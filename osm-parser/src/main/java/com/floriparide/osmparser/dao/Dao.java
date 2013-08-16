package com.floriparide.osmparser.dao;

import com.floriparide.osmparser.model.OSMRoute;
import com.floriparide.osmparser.model.OSMStop;
import com.floriparide.osmparser.model.Shape;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.zuq.osm.parser.model.OSMNode;

/**
 * Created by Mikhail Bragin
 */
public class Dao {

	DataSourceKeeper dataSourceKeeper;

	public Dao(DataSourceKeeper dataSourceKeeper) {
		this.dataSourceKeeper = dataSourceKeeper;
	}

	public void saveRoute(OSMRoute route) {

		String SQL = "INSERT INTO osm_route(id, short_name, long_name, route_from, route_to, agency)" +
				"    VALUES (?, ?, ?, ?, ?, ?)";
		Connection connection = null;

		try {
			connection = dataSourceKeeper.getConnection();

			PreparedStatement stmt = connection.prepareStatement(SQL);

			stmt.setLong(1, Long.parseLong(route.getRelation().id));
			stmt.setString(2, route.getRelation().tags.get("ref"));
			stmt.setString(3, route.getRelation().getName());
			stmt.setString(4, route.getRelation().tags.get("from"));
			stmt.setString(5, route.getRelation().tags.get("to"));
			stmt.setString(6, route.getRelation().tags.get("operator"));

			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceKeeper.closeConnection(connection);
		}
	}

	public void saveStops(List<OSMNode> stops) {
		String SQL = "INSERT INTO osm_stop(id, stop_name, bench, shelter, lat, lon)" +
				"    VALUES (?, ?, ?, ?, ?, ?)";
		Connection connection = null;

		try {
			connection = dataSourceKeeper.getConnection();

			PreparedStatement stmt = connection.prepareStatement(SQL);

			for (OSMNode node : stops) {

				stmt.setLong(1, Long.parseLong(node.id));

				if (node.tags.containsKey("name"))
					stmt.setString(2, node.tags.get("name"));
				else
					stmt.setNull(2, Types.VARCHAR);

				if (node.tags.containsKey("bench"))
					stmt.setBoolean(3, node.tags.get("bench").equals("yes") ? true : false);
				else
					stmt.setNull(3, Types.BOOLEAN);

				if (node.tags.containsKey("shelter"))
					stmt.setBoolean(4, node.tags.get("shelter").equals("yes") ? true : false);
				else
					stmt.setNull(4, Types.BOOLEAN);

				stmt.setDouble(5, Double.parseDouble(node.lat));
				stmt.setDouble(6, Double.parseDouble(node.lon));

				stmt.addBatch();
			}

			stmt.executeBatch();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceKeeper.closeConnection(connection);
		}
	}

	public void saveRouteStopsRelation(OSMRoute route, List<OSMNode> stops) {
		String SQL = "INSERT INTO osm_route_stop(route_id, stop_id, sequence) VALUES (?, ?, ?);";
		Connection connection = null;

		try {
			connection = dataSourceKeeper.getConnection();

			PreparedStatement stmt = connection.prepareStatement(SQL);

			long sequence = 0;
			for (OSMNode node : stops) {

				stmt.setLong(1, Long.parseLong(route.getRelation().id));
				stmt.setLong(2, Long.parseLong(node.id));
				stmt.setLong(3, sequence);

				stmt.addBatch();
				sequence++;
			}

			stmt.executeBatch();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceKeeper.closeConnection(connection);
		}
	}


	public void saveShape(OSMRoute route, List<OSMNode> shape) {
		String SQL = "INSERT INTO osm_shape(lat, lon, sequence, route_id)" +
				"    VALUES (?, ?, ?, ?);";
		Connection connection = null;

		try {
			connection = dataSourceKeeper.getConnection();

			PreparedStatement stmt = connection.prepareStatement(SQL);

			long sequence = 0;
			for (OSMNode node : shape) {

				stmt.setDouble(1, Double.parseDouble(node.lat));
				stmt.setDouble(2, Double.parseDouble(node.lon));
				stmt.setLong(3, sequence);
				stmt.setLong(4, Long.parseLong(route.getRelation().id));

				stmt.addBatch();
				sequence++;
			}

			stmt.executeBatch();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceKeeper.closeConnection(connection);
		}
	}


	public List<Shape> getShapeByRoute(long routeId) {
		List<Shape> result = new LinkedList<>();
		Connection con = dataSourceKeeper.getConnection();
		try {

			String SQL = "SELECT lat, lon, sequence FROM osm_shape WHERE route_id = " + routeId;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);

			while (rs.next()) {
				result.add(new Shape(
						rs.getDouble("lat"),
						rs.getDouble("lon"),
						rs.getLong("sequence")));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		} finally {
			DataSourceKeeper.closeConnection(con);
		}

		return result;
	}

	public List<Shape> getShapes() {
		List<Shape> result = new LinkedList<>();
		Connection con = dataSourceKeeper.getConnection();
		try {

			String SQL = "SELECT * FROM osm_shape";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);

			while (rs.next()) {
				result.add(new Shape(
						rs.getDouble("lat"),
						rs.getDouble("lon"),
						rs.getLong("sequence"),
						rs.getLong("route_id")));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		} finally {
			DataSourceKeeper.closeConnection(con);
		}

		return result;
	}

	public List<OSMStop> getStops() {

		List<OSMStop> result = new LinkedList<>();
		Connection con = dataSourceKeeper.getConnection();
		try {

			String SQL = "SELECT * FROM osm_stop";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);

			while (rs.next()) {
				result.add(new OSMStop(
						rs.getLong("id"),
						rs.getString("stop_name"),
						rs.getDouble("lat"),
						rs.getDouble("lon")));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		} finally {
			DataSourceKeeper.closeConnection(con);
		}

		return result;
	}
}
