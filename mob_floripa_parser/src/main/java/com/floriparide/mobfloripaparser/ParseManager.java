package com.floriparide.mobfloripaparser;

import com.floriparide.mobfloripaparser.dao.Dao;
import com.floriparide.mobfloripaparser.dao.DataSourceKeeper;
import com.floriparide.mobfloripaparser.model.Task;
import com.floriparide.mobfloripaparser.workers.AgencyArchiveWorker;
import com.floriparide.mobfloripaparser.workers.OnibusListPageWorker;
import com.floriparide.mobfloripaparser.workers.RouteArchiveWorker;
import com.floriparide.mobfloripaparser.workers.RoutePageWorker;

/**
 * @author mikhail.bragin
 * @since 8/10/13
 */
public class ParseManager {

	String mainUrl = "http://mobfloripa.com.br/onibus.php";

	OnibusListPageWorker mainPageWorker;
	RoutePageWorker routePageWorker;
	RouteArchiveWorker routeArchiveWorker;
	AgencyArchiveWorker agencyArchiveWorker;
	Dao dao;

	public ParseManager() throws Exception {
		dao = new Dao(new DataSourceKeeper());
		this.routePageWorker = new RoutePageWorker();
		this.routeArchiveWorker = new RouteArchiveWorker(dao);
		this.agencyArchiveWorker = new AgencyArchiveWorker(dao);
		this.mainPageWorker = new OnibusListPageWorker(routePageWorker, routeArchiveWorker, dao);
	}

	public void start() {
		mainPageWorker.addTask(new Task<String>() {
			@Override
			public String taskObject() {
				return mainUrl;
			}
		});
	}
}
