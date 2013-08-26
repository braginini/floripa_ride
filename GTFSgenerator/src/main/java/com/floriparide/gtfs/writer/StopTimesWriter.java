package com.floriparide.gtfs.writer;

import com.floriparide.gtfs.dao.NodeDao;
import com.floriparide.gtfs.dao.TripDao;
import com.floriparide.gtfs.model.Trip;
import com.floriparide.gtfs.model.osm.AbstractNode;

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
import java.util.List;

/**
 * Created by Mikhail Bragin
 */
public class StopTimesWriter extends AbstractGTFSFileWriter {

	TripDao tripDao;

	NodeDao nodeDao;

	public StopTimesWriter(TripDao tripDao, NodeDao nodeDao) {
		this.tripDao = tripDao;
		this.nodeDao = nodeDao;
	}

	@Override
	protected void writeContents() {

		try {
			List<Trip> trips = tripDao.getTripsWithShape();

			PrintWriter writer = null;
			try {
				writer = new PrintWriter(new BufferedWriter(new FileWriter("stop_times.txt", true)));

				for (Trip trip : trips) {

					List<AbstractNode> stops = nodeDao.getPlatformsInRelationSimpleOrdered(trip.getShapeId());

					java.util.Calendar calendar = trip.getStartTimeCalendar();
					DateFormat df = new SimpleDateFormat("hh:mm:ss");
					String startTime = df.format(calendar.getTime());
					calendar.add(Calendar.MINUTE, trip.getTripTime());
					String endTime = df.format(calendar.getTime());

					int sequence = 0;
					for (AbstractNode stop : stops) {
						if (stops.indexOf(stop) == 0) {
							writer.println(getStopTimeStringWithTime(trip, stop, startTime, sequence));
							continue;
						}
						if (stops.indexOf(stop) == stops.size() - 1) {
							writer.println(getStopTimeStringWithTime(trip, stop, endTime, sequence));
							continue;
						}

						writer.println(getStopTimeString(trip, stop, sequence));
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (writer != null) {
					writer.close();
				}
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void writeHeading() throws FileNotFoundException, UnsupportedEncodingException {

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

	@Override
	protected String getLine(Object obj) {
		return null;
	}

	private static String getStopTimeStringWithTime(Trip trip, AbstractNode stop, String time, int sequence) {
		StringBuilder sb = new StringBuilder()
				.append(trip.getId())
				.append(DELIMETER)
				.append(time)
				.append(DELIMETER)
				.append(time)
				.append(DELIMETER)
				.append(stop.getId())
				.append(DELIMETER)
				.append(sequence)
				.append(DELIMETER)
				.append(DELIMETER)
				.append(DELIMETER)
				.append(DELIMETER)
				.append(DELIMETER);

		return sb.toString();
	}

	private static String getStopTimeString(Trip trip, AbstractNode stop, int sequence) {
		StringBuilder sb = new StringBuilder()
				.append(trip.getId())
				.append(DELIMETER)
				.append(DELIMETER)
				.append(DELIMETER)
				.append(stop.getId())
				.append(DELIMETER)
				.append(sequence)
				.append(DELIMETER)
				.append(DELIMETER)
				.append(DELIMETER)
				.append(DELIMETER)
				.append(DELIMETER);

		return sb.toString();
	}
}
