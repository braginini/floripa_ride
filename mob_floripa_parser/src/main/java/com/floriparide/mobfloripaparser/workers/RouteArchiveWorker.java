package com.floriparide.mobfloripaparser.workers;

import com.floriparide.mobfloripaparser.dao.Dao;
import com.floriparide.mobfloripaparser.model.Route;
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
public class RouteArchiveWorker implements Worker<List<Route>> {

	Dao dao;
	ScheduledExecutorService executorService;

	List<Route> batch = Collections.synchronizedList(new ArrayList<Route>());

	ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	ReentrantReadWriteLock.ReadLock r = lock.readLock();
	ReentrantReadWriteLock.WriteLock w = lock.writeLock();

	public RouteArchiveWorker(Dao routeDao) {
		this.dao = routeDao;
		this.executorService = Executors.newScheduledThreadPool(1, new CustomThreadFactory("route-archive-worker"));
		this.executorService.scheduleAtFixedRate(new BatchInsert(), 0, 5, TimeUnit.SECONDS);
	}

	@Override
	public void addTask(Task<List<Route>> task) {

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

				dao.saveRoute(batch);
				batch = Collections.synchronizedList(new ArrayList<Route>());

			} finally {
				w.unlock();
			}
		}
	}
}
