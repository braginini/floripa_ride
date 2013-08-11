package com.floriparide.mobfloripaparser;

import com.floriparide.mobfloripaparser.model.Task;
import com.floriparide.mobfloripaparser.workers.OnibusListPageWorker;
import com.floriparide.mobfloripaparser.workers.RoutePageWorker;

/**
 * @author mikhail.bragin
 * @since 8/10/13
 */
public class ParseManager {

    String mainUrl = "http://mobfloripa.com.br/onibus.php";

    OnibusListPageWorker mainPageWorker;
    RoutePageWorker routePageWorker;

    public ParseManager() {
        this.routePageWorker = new RoutePageWorker();
        this.mainPageWorker = new OnibusListPageWorker(routePageWorker);
    }

    public void start() {
        mainPageWorker.addTask(new Task() {
           @Override
           public String getUrl() {
               return mainUrl;
           }
       });
    }
}
