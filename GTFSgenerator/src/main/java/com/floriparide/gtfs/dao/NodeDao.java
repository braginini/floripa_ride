package com.floriparide.gtfs.dao;

import com.floriparide.gtfs.model.Node;

import org.postgis.PGgeometry;
import org.postgis.Point;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Mikhail Bragin
 */
public class NodeDao extends Dao {

	public NodeDao(DataSourceKeeper dataSourceKeeper) {
		super(dataSourceKeeper);
	}

	public List<Node> getNodesWithTag(String tagKey, String tagValue) {

		List<Node> nodes = new ArrayList<>();

		Connection con = dataSourceKeeper.getConnection();
		try {

			String SQL = "SELECT n.id, n.geom FROM nodes n, node_tags t " +
					"WHERE n.id = t.node_id " +
					"AND t.k = '" + tagKey + "' " +
					"AND t.v = '" + tagValue + "' " +
					"GROUP BY n.id";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);

			while (rs.next()) {

				long id = rs.getLong("id");
				Point point = (Point) ((PGgeometry) rs.getObject("geom")).getGeometry();

				nodes.add(new Node(id, new HashMap<String, String>(), point.getY(), point.getX()));

			}

		} catch (Exception e) {
            e.printStackTrace();
			System.exit(0);
		} finally {
			DataSourceKeeper.closeConnection(con);
		}

		for (Node node : nodes) {
			node.setTags(getNodeTags(node.getId()));
		}

		return nodes;

	}

	public Map<String, String> getNodeTags(long nodeId) {

		Connection con = dataSourceKeeper.getConnection();
		try {

			String SQL = "SELECT * from node_tags WHERE node_id = " + nodeId;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);

			Map<String, String> tags = new HashMap<>();

			while (rs.next()) {
				tags.put(rs.getString("k"), rs.getString("v"));
			}

			return tags;

		} catch (Exception e) {
            e.printStackTrace();
			System.exit(0);
		} finally {
			DataSourceKeeper.closeConnection(con);
		}

		return Collections.emptyMap();

	}

	public List<Node> getNodesInWayOrdered(long wayId) {

		List<Node> nodes = new LinkedList<>();

		Connection con = dataSourceKeeper.getConnection();
		try {

			String SQL = "SELECT * FROM nodes n, way_nodes w " +
					"WHERE n.id = w.node_id " +
					"AND w.way_id = " + wayId + " " +
                    "ORDER BY sequence_id";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);

			while (rs.next()) {

				long id = rs.getLong("id");
				Point point = (Point) ((PGgeometry) rs.getObject("geom")).getGeometry();

				nodes.add(new Node(id, new HashMap<String, String>(), point.getY(), point.getX()));

			}

		} catch (Exception e) {
            e.printStackTrace();
			System.exit(0);
		} finally {
			DataSourceKeeper.closeConnection(con);
		}

		return nodes;

	}
}
