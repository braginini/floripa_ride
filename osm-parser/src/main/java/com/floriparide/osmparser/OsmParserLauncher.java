package com.floriparide.osmparser;

import com.floriparide.osmparser.dao.Dao;
import com.floriparide.osmparser.dao.DataSourceKeeper;
import com.floriparide.osmparser.model.OSMRoute;
import com.floriparide.osmparser.model.Shape;
import com.floriparide.osmparser.parser.RouteParser;

import java.util.List;

import br.zuq.osm.parser.model.OSMNode;

/**
 * Created by Mikhail Bragin
 */
public class OsmParserLauncher {

	public static void main(String[] args) throws Exception {

		Dao dao = new Dao(new DataSourceKeeper());

		RouteParser parser = new RouteParser(args[0]);
		List<OSMRoute> routeList = parser.parse();

		for (OSMRoute route : routeList) {
			dao.saveRoute(route);

			for (OSMNode stop : route.getStops()) {
				dao.saveStop(stop);
			}
			dao.saveRouteStopsRelation(route, route.getStops());
			dao.saveShape(route, route.getShape());

		}
	}
}
