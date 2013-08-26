package com.floriparide.gtfs.writer;

import com.floriparide.gtfs.dao.TripDao;
import com.floriparide.gtfs.model.Trip;

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
public class TripsWriter extends AbstractGTFSFileWriter<Trip> {

	TripDao tripDao;

	public TripsWriter(TripDao tripDao) {
		this.tripDao = tripDao;
	}

	@Override
	protected void writeContents() {

		List<Trip> trips = tripDao.getTripsWithShapeSimple();

		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter("trips.txt", true)));

			for (Trip trip : trips)
				writer.println(getLine(trip));

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

	@Override
	protected String getLine(Trip trip) {
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
}
