package com.floriparide.mobfloripaparser.workers;

import com.floriparide.mobfloripaparser.dao.Dao;
import com.floriparide.mobfloripaparser.model.Agency;
import com.floriparide.mobfloripaparser.model.ParseResult;
import com.floriparide.mobfloripaparser.model.Route;
import com.floriparide.mobfloripaparser.model.Task;
import com.floriparide.mobfloripaparser.parser.OnibusListPageParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author mikhail.bragin
 * @since 8/10/13
 */
public class OnibusListPageWorker implements Worker<String> {

    ExecutorService executorService;

    RoutePageWorker routePageWorker;

	RouteArchiveWorker routeArchiveWorker;

	AgencyArchiveWorker agencyArchiveWorker;

	Dao dao;

    public OnibusListPageWorker(RoutePageWorker routePageWorker, RouteArchiveWorker routeArchiveWorker, Dao dao) {
        this.routePageWorker = routePageWorker;
	    this.routeArchiveWorker = routeArchiveWorker;
	    this.dao = dao;
        this.executorService = Executors.newFixedThreadPool(1, new CustomThreadFactory("main-page-worker"));
    }

    @Override
    public void addTask(final Task<String> task) {

        executorService.submit(new Runnable() {
            @Override
            public void run() {
               try {

                   Document pageDoc =  Jsoup.parse(new URL(task.taskObject()), 20 * 1000);
	               ParseResult<List<Route>> parseResult = new OnibusListPageParser(pageDoc).parse();
	               final List<Route> routes = parseResult.result();

	               final Map<String, Agency> map = new HashMap<>();

	               List<Agency> agencies = dao.getAgencies();

	               Map<String, Agency> agencyMap = new HashMap<>();

	               for (Agency agency : agencies) {
		               agencyMap.put(agency.getName(), agency);
	               }

	               for (Route route : routes) {
		               Agency agency = route.getAgency();
		               route.setAgencyId(agencyMap.get(agency.getName()).getId());
	               }

	               routeArchiveWorker.addTask(new Task<List<Route>>() {
		               @Override
		               public List<Route> taskObject() {
			               return routes;
		               }
	               });

               } catch (IOException e) {
                  e.printStackTrace();
               }
            }
        });
    }
}
