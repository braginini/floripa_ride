package com.floriparide.osmparser.parser;

import com.floriparide.osmparser.model.OSMRoute;

import java.util.ArrayList;
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
						if (!member.role.equals("platform"))
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
}
