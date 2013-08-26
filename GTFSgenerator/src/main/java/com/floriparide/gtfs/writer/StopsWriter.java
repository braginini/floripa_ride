package com.floriparide.gtfs.writer;

import com.floriparide.gtfs.dao.NodeDao;
import com.floriparide.gtfs.dao.WayDao;
import com.floriparide.gtfs.model.Node;
import com.floriparide.gtfs.model.Way;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;

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
public class StopsWriter extends AbstractGTFSFileWriter<Node> {

	NodeDao nodeDao;
	WayDao wayDao;

	public StopsWriter(NodeDao dao, WayDao wayDao) {
		this.nodeDao = dao;
		this.wayDao = wayDao;
	}

	@Override
	protected void writeContents() {
		List<Node> stops = nodeDao.getNodesWithTag("highway", "bus_stop");

		List<Way> busStations = wayDao.getWaysWithTag("amenity", "bus_station");

		for (Way way : busStations) {
			stops.add(getTerminalCenter(way));
		}

		if (stops.isEmpty())
			return;

		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter("stops.txt", true)));

			for (Node node : stops) {
				writer.println(getLine(node));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}

	@Override
	protected void writeHeading() throws FileNotFoundException, UnsupportedEncodingException {

		PrintWriter writer = new PrintWriter("stops.txt", "UTF-8");

		StringBuilder sb = new StringBuilder()
				.append("stop_id")
				.append(DELIMETER)
				.append("stop_code")
				.append(DELIMETER)
				.append("stop_name")
				.append(DELIMETER)
				.append("stop_desc")
				.append(DELIMETER)
				.append("stop_lat")
				.append(DELIMETER)
				.append("stop_lon")
				.append(DELIMETER)
				.append("zone_id")
				.append(DELIMETER)
				.append("stop_url")
				.append(DELIMETER)
				.append("location_type")
				.append(DELIMETER)
				.append("direction")
				.append(DELIMETER)
				.append("position");

		writer.println(sb.toString());
		writer.close();
	}

	@Override
	protected String getLine(Node node) {

		StringBuilder sb = new StringBuilder()
				.append(node.getId())
				.append(DELIMETER)
				.append(DELIMETER)
				.append((node.getTags().containsKey("name")) ? node.getTags().get("name") : node.getId())
				.append(DELIMETER)
				.append(DELIMETER)
				.append(node.getLat())
				.append(DELIMETER)
				.append(node.getLon())
				.append(DELIMETER)
				.append(DELIMETER)
				.append(DELIMETER)
				.append(DELIMETER)
				.append(DELIMETER);

		return sb.toString();
	}

	private Node getTerminalCenter(Way way) {

		Point[] points = new Point[way.getNodes().size()];
		for (int i = 0; i < way.getNodes().size(); i++) {
			Coordinate[] coordinates = new Coordinate[]{
					new Coordinate(
							way.getNodes().get(i).getLon(),
							way.getNodes().get(i).getLat())};

			points[i] = (new Point(
					new CoordinateArraySequence(coordinates),
					new GeometryFactory(new PrecisionModel())));
		}

		Geometry geometry = new MultiPoint(points, new GeometryFactory(new PrecisionModel()));

		return new Node(way.getId(), way.getTags(), geometry.getCentroid().getY(), geometry.getCentroid().getX());
	}
}
