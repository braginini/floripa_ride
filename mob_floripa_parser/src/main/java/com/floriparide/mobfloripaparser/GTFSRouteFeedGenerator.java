package com.floriparide.mobfloripaparser;

import com.floriparide.mobfloripaparser.dao.Dao;
import com.floriparide.mobfloripaparser.dao.DataSourceKeeper;
import com.floriparide.mobfloripaparser.model.OSMStop;
import com.floriparide.mobfloripaparser.model.Route;
import com.floriparide.mobfloripaparser.model.Trip;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
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
		Route route = dao.getRoute(577l);

		//writeRoute(route);

		writeTrips();

		writeStopTimes();
	}

	private static void writeStopTimes() throws FileNotFoundException, UnsupportedEncodingException, ParseException {

		writeStopTimesFileHeading();

		List<Trip> trips = dao.getTripsWithShape();

		for (Trip trip : trips) {
			List<OSMStop> stops = dao.getStopsByOsmRoute(trip.getShapeId());
			Collections.sort(stops);
			java.util.Calendar calendar = trip.getStartTimeCalendar();
			DateFormat df = new SimpleDateFormat("HH:mm:ss");
			String startTime = df.format(calendar.getTime());
			calendar.add(Calendar.MINUTE, trip.getTripTime());
			String endTime = df.format(calendar.getTime());
			for (OSMStop stop : stops) {
				if (stops.indexOf(stop) == 0) {
					writeStopTimeWithTime(trip, stop, startTime);
					continue;
				}
				if (stops.indexOf(stop) == stops.size() - 1) {
					writeStopTimeWithTime(trip, stop, endTime);
					continue;
				}

				writeStopTime(trip, stop);
			}
		}
	}

	private static void writeStopTimeWithTime(Trip trip, OSMStop stop, String time) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter("stop_times.txt", true)));
			writer.println(getStopTimeStringWithTime(trip, stop, time));
		} catch (IOException e) {
			//oh noes!
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	private static void writeStopTime(Trip trip, OSMStop stop) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter("stop_times.txt", true)));
			writer.println(getStopTimeString(trip, stop));
		} catch (IOException e) {
			//oh noes!
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	private static String getStopTimeStringWithTime(Trip trip, OSMStop stop, String time) {
		StringBuilder sb = new StringBuilder()
				.append(trip.getId())
				.append(DELIMETER)
				.append(time)
				.append(DELIMETER)
				.append(time)
				.append(DELIMETER)
				.append(stop.getId())
				.append(DELIMETER)
				.append(stop.getSequence())
				.append(DELIMETER)
				.append(DELIMETER)
				.append(DELIMETER)
				.append(DELIMETER)
				.append(DELIMETER);

		return sb.toString();
	}

	private static String getStopTimeString(Trip trip, OSMStop stop) {
		StringBuilder sb = new StringBuilder()
				.append(trip.getId())
				.append(DELIMETER)
				.append(DELIMETER)
				.append(DELIMETER)
				.append(stop.getId())
				.append(DELIMETER)
				.append(stop.getSequence())
				.append(DELIMETER)
				.append(DELIMETER)
				.append(DELIMETER)
				.append(DELIMETER)
				.append(DELIMETER);

		return sb.toString();
	}

	private static void writeStopTimesFileHeading() throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("stop_times.txt", "UTF-8");

		StringBuilder sb = new StringBuilder();
		sb.append("trip_id");
		sb.append(DELIMETER);
		sb.append("arrival_time");
		sb.append(DELIMETER);
		sb.append("departure_time");
		sb.append(DELIMETER);
		sb.append("stop_id");
		sb.append(DELIMETER);
		sb.append("stop_sequence");
		sb.append(DELIMETER);
		sb.append("stop_headsign");
		sb.append(DELIMETER);
		sb.append("pickup_type");
		sb.append(DELIMETER);
		sb.append("drop_off_type");
		sb.append(DELIMETER);
		sb.append("shape_dist_traveled");
		sb.append(DELIMETER);
		sb.append("timepoint");

		writer.println(sb.toString());
		writer.close();

	}

	private static void writeTrips() throws FileNotFoundException, UnsupportedEncodingException {

		writeTripFileHeading();
		List<Trip> trips = dao.getTripsWithShapeSimple();

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
