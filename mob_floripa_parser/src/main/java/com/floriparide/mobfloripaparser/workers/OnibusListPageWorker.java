package com.floriparide.mobfloripaparser.workers;

import com.floriparide.mobfloripaparser.model.ParseResult;
import com.floriparide.mobfloripaparser.model.Route;
import com.floriparide.mobfloripaparser.model.Task;
import com.floriparide.mobfloripaparser.parser.OnibusListPageParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author mikhail.bragin
 * @since 8/10/13
 */
public class OnibusListPageWorker implements Worker {

    ExecutorService executorService;

    RoutePageWorker routePageWorker;

    public OnibusListPageWorker(RoutePageWorker routePageWorker) {
        this.routePageWorker = routePageWorker;
        this.executorService = Executors.newFixedThreadPool(1, new CustomThreadFactory("main-page-worker"));
    }

    @Override
    public void addTask(final Task task) {

        executorService.submit(new Runnable() {
            @Override
            public void run() {
               try {
                   Document pageDoc =  Jsoup.parse(new URL(task.getUrl()), 20 * 1000);


                   ParseResult<List<Route>> parseResult = new OnibusListPageParser(pageDoc).parse();
                   List<Route> routes = parseResult.result();

               } catch (IOException e) {
                  e.printStackTrace();
               }
            }
        });
    }
}
