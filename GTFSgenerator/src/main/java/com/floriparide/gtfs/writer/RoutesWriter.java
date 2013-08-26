package com.floriparide.gtfs.writer;

import com.floriparide.gtfs.dao.RouteDao;
import com.floriparide.gtfs.model.Route;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Mikhail Bragin
 */
public class RoutesWriter extends AbstractGTFSFileWriter<Route> {

	RouteDao routeDao;

	public RoutesWriter(RouteDao routeDao) {
		this.routeDao = routeDao;
	}

	@Override
	protected void writeContents() {

		List<Route> routes = routeDao.getActiveRoutes();
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter("routes.txt", true)));

			for (Route route : routes) {
				writer.println(getLine(route));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}

	}

	@Override
	protected void writeHeading() throws FileNotFoundException, UnsupportedEncodingException {

		PrintWriter writer = new PrintWriter("routes.txt", "UTF-8");

		writer.println("route_id" + DELIMETER + "route_short_name" + DELIMETER + "route_long_name" + DELIMETER + "route_type" + DELIMETER + "route_url");
		writer.close();
	}

	@Override
	protected String getLine(Route route) {

		StringBuilder sb = new StringBuilder()
				.append(route.getId())
				.append(DELIMETER)
				.append(route.getShortName())
				.append(DELIMETER)
				.append(route.getLongName())
				.append(DELIMETER)
				.append(route.getType().ordinal())
				.append(DELIMETER);

		return sb.toString();
	}
}
