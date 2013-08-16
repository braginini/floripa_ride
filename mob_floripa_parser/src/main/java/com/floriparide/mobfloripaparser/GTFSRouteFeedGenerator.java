package com.floriparide.mobfloripaparser;

import com.floriparide.mobfloripaparser.dao.Dao;
import com.floriparide.mobfloripaparser.dao.DataSourceKeeper;
import com.floriparide.mobfloripaparser.model.Route;
import com.floriparide.mobfloripaparser.model.Trip;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Mikhail Bragin
 */
public class GTFSRouteFeedGenerator {

	public static final String DELIMETER = ",";

	public static Dao dao;

	public static void main(String[] args) throws Exception {

		dao = new Dao(new DataSourceKeeper());

		//routes.txt
		Route route = dao.getRoute(478l);


		//writeRoute(route);

		writeTrips();
	}

	private static void writeTrips() throws FileNotFoundException, UnsupportedEncodingException {

		writeTripFileHeading();
		List<Trip> trips = dao.getTripsWithShape();

		for (Trip trip : trips) {
			writeTrip(trip);
		}

	}

	private static void writeRoute(Route route) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("routes.txt", "UTF-8");


		writer.println("route_id" + DELIMETER + "route_short_name" + DELIMETER + "route_long_name" + DELIMETER + "route_type" + DELIMETER + "route_url");
		writer.println(route.getId() + DELIMETER + route.getShortName() + DELIMETER + route.getLongName() + DELIMETER + route.getType().ordinal() + DELIMETER);
		writer.close();

	}

	private static void writeTrip(Trip trip) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter("trips.txt", true)));
			writer.println(getTripString(trip));
		} catch (IOException e) {
			//oh noes!
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	private static String getTripString(Trip trip) {
		StringBuilder sb = new StringBuilder()
				.append(trip.getRouteId())
				.append(DELIMETER)
				.append(trip.getServiceId())
				.append(DELIMETER)
				.append(trip.getId())
				.append(DELIMETER)
				.append(DELIMETER)
				.append(DELIMETER)
				.append(trip.getShapeId())
				.append(DELIMETER);

		return sb.toString();
	}

	private static void writeTripFileHeading() throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("trips.txt", "UTF-8");

		StringBuilder sb = new StringBuilder();
		sb.append("route_id");
		sb.append(DELIMETER);
		sb.append("service_id");
		sb.append(DELIMETER);
		sb.append("trip_id");
		sb.append(DELIMETER);
		sb.append("direction_id");
		sb.append(DELIMETER);
		sb.append("block_id");
		sb.append(DELIMETER);
		sb.append("shape_id");
		sb.append(DELIMETER);
		sb.append("trip_type");

		writer.println(sb.toString());
		writer.close();
	}
}
