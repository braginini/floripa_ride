package com.floriparide.mobfloripaparser.workers;

import com.floriparide.mobfloripaparser.model.Route;
import com.floriparide.mobfloripaparser.model.Task;
import com.floriparide.mobfloripaparser.model.Trip;
import com.floriparide.mobfloripaparser.parser.RoutePageParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author mikhail.bragin
 * @since 8/10/13
 */
public class RoutePageWorker implements Worker<Route> {

	ExecutorService executorService;


	public RoutePageWorker() {
		this.executorService = Executors.newFixedThreadPool(1, new CustomThreadFactory("route-page-parse-worker"));
	}

	@Override
	public void addTask(final Task<Route> task) {

		executorService.submit(new Runnable() {

			@Override
			public void run() {
				try {
					Document document = Jsoup.parse(new URL(task.taskObject().getParseUrl()), 20 * 1000);

					List<Trip> trips = new RoutePageParser(document, task.taskObject()).parse().result();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
