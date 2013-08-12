package com.floriparide.mobfloripaparser.workers;

import com.floriparide.mobfloripaparser.dao.Dao;
import com.floriparide.mobfloripaparser.model.Agency;
import com.floriparide.mobfloripaparser.model.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Mikhail Bragin
 */
public class AgencyArchiveWorker implements Worker<List<Agency>> {

	Dao dao;
	ScheduledExecutorService executorService;

	List<Agency> batch = Collections.synchronizedList(new ArrayList<Agency>());

	ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	ReentrantReadWriteLock.ReadLock r = lock.readLock();
	ReentrantReadWriteLock.WriteLock w = lock.writeLock();

	public AgencyArchiveWorker(Dao routeDao) {
		this.dao = routeDao;
		this.executorService = Executors.newScheduledThreadPool(1, new CustomThreadFactory("route-archive-worker"));
		this.executorService.scheduleAtFixedRate(new BatchInsert(), 0, 5, TimeUnit.SECONDS);
	}

	@Override
	public void addTask(Task<List<Agency>> task) {

		try {
			r.lock();
			batch.addAll(task.taskObject());
		} finally {
			r.unlock();
		}
	}

	private class BatchInsert implements Runnable {

		@Override
		public void run() {
			try {
				w.lock();

				if (batch.isEmpty())
					return;

				dao.saveAgency(batch);
				batch = Collections.synchronizedList(new ArrayList<Agency>());

			} finally {
				w.unlock();
			}
		}
	}
}
