package com.floriparide.osmparser.parser;

import com.floriparide.osmparser.model.OSMRoute;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;
import com.vividsolutions.jts.util.GeometricShapeFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.zuq.osm.parser.OSMParser;
import br.zuq.osm.parser.model.Member;
import br.zuq.osm.parser.model.OSM;
import br.zuq.osm.parser.model.OSMNode;
import br.zuq.osm.parser.model.Relation;
import br.zuq.osm.parser.model.Way;

/**
 * Created by Mikhail Bragin
 */
public class RouteParser {

	String path;

	OSM osm;

	public RouteParser(String path) {
		this.path = path;
	}

	public List<OSMRoute> parse() throws Exception {

		osm = OSMParser.parse(path);

		List<Relation> routes = new ArrayList<>();

		if (osm != null) {

			for (Relation relation : osm.getRelations()) {

				if (relation.tags.containsKey("type"))
					routes.add(relation);
			}
		}

		List<OSMRoute> osmRoutes = new ArrayList<>();
		for (Relation relation : routes) {

			Set<String> platformIds = new LinkedHashSet<>();
			Set<String> wayIds = new LinkedHashSet<>();

			for (Member member : relation.members) {

				String id = member.ref;
				switch (member.type) {
					case "node":
						platformIds.add(id);
						break;
					case "way":
						if (member.role.equals("platform")) {  //here the OSM way with tag platform is the terminal
							platformIds.add(id);
							break;
						}
						wayIds.add(id);
						break;
				}
			}

			Map<String, OSMNode> platforms = new LinkedHashMap<>();
			Map<String, Way> ways = new LinkedHashMap<>();

			for (OSMNode node : osm.getNodes()) {

				if (platformIds.contains(node.id))
					platforms.put(node.id, node);
			}

			for (Way way : osm.getWays()) {
				if (platformIds.contains(way.id)) {
					//here that OSM public transport station (platform) defined as way converted to OSMNode to platform
					Point centroid = getTerminalCenter(way);
					platforms.put(way.id,
							new OSMNode(
									way.id,
									way.visible,
									way.timestamp,
									way.version,
									way.changeset,
									way.user,
									way.uid,
									String.valueOf(centroid.getY()),
									String.valueOf(centroid.getX()),
									way.tags));
				}
				if (wayIds.contains(way.id))
					ways.put(way.id, way);
			}

			//here we keep the order
			List<OSMNode> orderedPlatforms = new LinkedList<>();
			for (String platformId : platformIds) {
				orderedPlatforms.add(platforms.get(platformId));
			}

			List<Way> orderedWays = new LinkedList<>();
			for (String wayId : wayIds) {
				orderedWays.add(ways.get(wayId));
			}


			//prepare shapes
			List<OSMNode> shapePoints = new LinkedList<>();
			for (Way way : orderedWays) {
				for (OSMNode node : way.nodes) {
					shapePoints.add(node);
				}
			}

			osmRoutes.add(new OSMRoute(relation, shapePoints, orderedPlatforms));

		}

		return osmRoutes;
	}

	private Point getTerminalCenter(Way way) {

		Point[] points = new Point[way.nodes.size()];
		for (int i = 0; i < way.nodes.size(); i++) {
			Coordinate[] coordinates = new Coordinate[]{
					new Coordinate(
							Double.parseDouble(way.nodes.get(i).lon),
							Double.parseDouble(way.nodes.get(i).lat))};

			points[i] = (new Point(
					new CoordinateArraySequence(coordinates),
					new GeometryFactory(new PrecisionModel())));
		}

		Geometry geometry = new MultiPoint(points, new GeometryFactory(new PrecisionModel()));

		return geometry.getCentroid();
	}
}
