package com.floriparide.osmparser;

import com.floriparide.osmparser.dao.Dao;
import com.floriparide.osmparser.dao.DataSourceKeeper;
import com.floriparide.osmparser.model.OSMStop;

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
public class GTFSRouteFeedGenerator {

	public static final String DELIMETER = ",";

	public static void main(String[] args) throws Exception {

		Dao dao = new Dao(new DataSourceKeeper());

		//routes.txt
		writeStopFileHeading();

		List<OSMStop> stops = dao.getStops();

		for (OSMStop stop : stops)
			writeStop(stop);
	}

	private static void writeStop(OSMStop stop) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter("stops.txt", true)));
			writer.println(getStopString(stop));
		} catch (IOException e) {
			//oh noes!
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	private static String getStopString(OSMStop stop) {
		StringBuilder sb = new StringBuilder()
				.append(stop.getId())
				.append(DELIMETER)
				.append(DELIMETER)
				.append((stop.getName() != null) ? stop.getName() : DELIMETER)
				.append(DELIMETER)
				.append(stop.getLat())
				.append(DELIMETER)
				.append(stop.getLon())
				.append(DELIMETER)
				.append(DELIMETER)
				.append(DELIMETER)
				.append(DELIMETER)
				.append(DELIMETER)
				.append(DELIMETER);

		return sb.toString();
	}

	private static void writeStopFileHeading() throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("stops.txt", "UTF-8");

		StringBuilder sb = new StringBuilder();
		sb.append("stop_id");
		sb.append(DELIMETER);
		sb.append("stop_code");
		sb.append(DELIMETER);
		sb.append("stop_name");
		sb.append(DELIMETER);
		sb.append("stop_desc");
		sb.append(DELIMETER);
		sb.append("stop_lat");
		sb.append(DELIMETER);
		sb.append("stop_lon");
		sb.append(DELIMETER);
		sb.append("zone_id");
		sb.append(DELIMETER);
		sb.append("stop_url");
		sb.append(DELIMETER);
		sb.append("location_type");
		sb.append(DELIMETER);
		sb.append("direction");
		sb.append(DELIMETER);
		sb.append("position");

		writer.println(sb.toString());
		writer.close();
	}

	/*private static void writeRoute(Route route) throws FileNotFoundException, UnsupportedEncodingException {

		writer.println(route.getId() + DELIMETER + route.getShortName() + DELIMETER + route.getLongName() + DELIMETER + DELIMETER + route.getType().ordinal());
		writer.close();

	}*/
}
