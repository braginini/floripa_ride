package com.floriparide.mobfloripaparser.parser;

import com.floriparide.mobfloripaparser.model.Agency;
import com.floriparide.mobfloripaparser.model.ParseResult;
import com.floriparide.mobfloripaparser.model.Route;
import com.floriparide.mobfloripaparser.model.Station;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mikhail.bragin
 * @since 8/11/13
 */
public class OnibusListPageParser implements PageParser<List<Route>> {

    Document document;

    public OnibusListPageParser(Document document) {
        this.document = document;
    }

    @Override
    public ParseResult<List<Route>> parse() {

        List<Element> linesEls1 = document.getElementsByAttributeValue("bgcolor", "#f7f7f7");
        List<Element> linesEls2 = document.getElementsByAttributeValue("bgcolor", "#ececec");
        linesEls1.addAll(linesEls2);

        List<Route> routes = new ArrayList<>(linesEls1.size());

        for (Element element : linesEls1) {

            List<Element> innerElements = element.getElementsByTag("td");

            String routeUrl = innerElements.get(0).getElementsByTag("a").attr("href");
            String shortName = innerElements.get(0).getElementsByTag("a").text();

            String longName = innerElements.get(1).getElementsByTag("a").text();

            String agencyUrl = innerElements.get(2).getElementsByTag("a").attr("href");
            String agencyName = innerElements.get(2).getElementsByTag("a").text();

            String origin = innerElements.get(3).text();
            String destination = innerElements.get(4).text();

            Route route = new Route(longName, shortName, Route.RouteType.BUS);
            route.setParseUrl(routeUrl);
            route.setOrigin(new Station(origin));
            route.setDestination(new Station(destination));
            route.setAgency(new Agency(agencyName, agencyUrl));

            routes.add(route);
        }

        return new RouteListParseResult(routes);
    }

    private class RouteListParseResult implements ParseResult<List<Route>> {

        List<Route> routes;

        private RouteListParseResult(List<Route> routes) {
            this.routes = routes;
        }

        @Override
        public List<Route> result() {
            return routes;
        }

        @Override
        public void setResult(List<Route> result) {
            this.routes = result;
        }
    }
}
