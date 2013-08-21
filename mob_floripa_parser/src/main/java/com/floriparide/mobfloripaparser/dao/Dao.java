package com.floriparide.mobfloripaparser.dao;

import com.floriparide.mobfloripaparser.model.Agency;
import com.floriparide.mobfloripaparser.model.OSMStop;
import com.floriparide.mobfloripaparser.model.Route;
import com.floriparide.mobfloripaparser.model.Trip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mikhail Bragin
 */
public class Dao {

	DataSourceKeeper dataSourceKeeper;
	private List<Agency> agencies;

	public Dao(DataSourceKeeper dataSourceKeeper) {
		this.dataSourceKeeper = dataSourceKeeper;
	}

	public void saveRoute(List<Route> routes) {

		String SQL = "INSERT INTO route (short_name, long_name, agency_id, route_type, descr, url, active) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?)";
		Connection connection = null;

		try {
			connection = dataSourceKeeper.getConnection();

			PreparedStatement stmt = connection.prepareStatement(SQL);

			for (Route route : routes) {
				stmt.setString(1, route.getShortName());
				stmt.setString(2, route.getLongName());
				System.out.println(route.getLongName());
				stmt.setLong(3, route.getAgencyId());   //todo get agencies
				stmt.setShort(4, (short) route.getType().ordinal());
				stmt.setString(5, route.getDescription());
				stmt.setString(6, route.getParseUrl());
				stmt.setBoolean(7, false);

				stmt.addBatch();
			}

			int[] rs = stmt.executeBatch();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceKeeper.closeConnection(connection);
		}
	}

	public void saveAgency(List<Agency> batch) {

		String SQL = "INSERT INTO agency (agency_name, url) " +
				"VALUES (?, ?)";
		Connection connection = null;

		try {
			connection = dataSourceKeeper.getConnection();

			PreparedStatement stmt = connection.prepareStatement(SQL);

			for (Agency agency : batch) {
				stmt.setString(1, agency.getName());
				stmt.setString(2, agency.getUrl());
				stmt.addBatch();
			}

			int[] rs = stmt.executeBatch();
			//connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataSourceKeeper.closeConnection(connection);
		}

	}

	public List<Agency> getAgencies() {

		List<Agency> result = new ArrayList<>();
		Connection con = dataSourceKeeper.getConnection();
		try {

			String SQL = "SELECT * FROM agency";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);

			while (rs.next()) {
				result.add(new Agency(
						rs.getLong("id"),
						rs.getString("agency_name"),
						rs.getString("url")));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		} finally {
			DataSourceKeeper.closeConnection(con);
		}

		return result;
	}

	public List<Route> getAllRoutesSimple() {

		List<Route> result = new ArrayList<>();
		Connection con = dataSourceKeeper.getConnection();
		try {

			String SQL = "SELECT id, url, short_name FROM route";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);

			while (rs.next()) {
				result.add(new Route(
						rs.getLong("id"),
						rs.getString("short_name"),
						rs.getString("url")));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		} finally {
			DataSourceKeeper.closeConnection(con);
		}

		return result;
	}

	public void saveTrip(List<Trip> batch) {
		String SQL = "INSERT INTO trip (route_id, service_id, trip_time, start_time, direction) " +
				"VALUES (?, ?, ?, ?, ?)";
		Connection connection = null;

		try {
			connection = dataSourceKeeper.getConnection();

			PreparedStatement stmt = connection.prepareStatement(SQL);

			for (Trip trip : batch) {
				stmt.setLong(1, trip.getRouteId());
				stmt.setLong(2, trip.getCalendarId());
				if (trip.getTripTime() != null)
					stmt.setInt(3, trip.getTripTime());
				else
					stmt.setNull(3, Types.INTEGER);
				stmt.setString(4, trip.getStartTime());

				stmt.setBoolean(5, trip.getDirection().getId());
				stmt.addBatch();
			}

			int[] rs = stmt.executeBatch();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceKeeper.closeConnection(connection);
		}
	}

	public Route getRoute(long routeId) {

		Connection con = dataSourceKeeper.getConnection();
		try {

			String SQL = "SELECT id, long_name, short_name FROM route WHERE id=" + routeId;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);

			while (rs.next()) {

				long id = rs.getLong("id");
				String longName = rs.getString("long_name");
				String shortName = rs.getString("short_name");
				Route route = new Route(id, longName, shortName, Route.RouteType.BUS);
				return route;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		} finally {
			DataSourceKeeper.closeConnection(con);
		}

		return null;
	}

	public List<Trip> getTripsWithShapeSimple() {

		List<Trip> result = new ArrayList<>();
		Connection con = dataSourceKeeper.getConnection();
		try {

			String SQL = "SELECT id, route_id, service_id, osm_route_id FROM trip WHERE osm_route_id IS NOT NULL";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);

			while (rs.next()) {
				result.add(new Trip(
						rs.getLong("id"),
						rs.getLong("route_id"),
						rs.getLong("service_id"),
						rs.getLong("osm_route_id")));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		} finally {
			DataSourceKeeper.closeConnection(con);
		}

		return result;
	}

	public List<Trip> getTripsWithShape() {

		List<Trip> result = new ArrayList<>();
		Connection con = dataSourceKeeper.getConnection();
		try {

			String SQL = "SELECT id, route_id, service_id, osm_route_id, start_time, trip_time  FROM trip WHERE osm_route_id IS NOT NULL";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);

			while (rs.next()) {
				result.add(new Trip(
						rs.getLong("id"),
						rs.getLong("route_id"),
						rs.getLong("service_id"),
						rs.getLong("osm_route_id"),
						rs.getString("start_time"),
						rs.getInt("trip_time")));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		} finally {
			DataSourceKeeper.closeConnection(con);
		}

		return result;
	}

	public List<OSMStop> getStopsByOsmRoute(long osmRouteId) {

		List<OSMStop> result = new LinkedList<>();
		Connection con = dataSourceKeeper.getConnection();
		try {

			String SQL = "SELECT os.*, ors.sequence FROM osm_stop os, osm_route_stop ors WHERE os.id = ors.stop_id " +
					"AND ors.route_id = " + osmRouteId;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);

			while (rs.next()) {
				result.add(new OSMStop(
						rs.getLong("id"),
						rs.getString("stop_name"),
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

	public List<Route> getActiveRoutes() {
		Connection con = dataSourceKeeper.getConnection();
		List<Route> result = new ArrayList<>();
		try {

			String SQL = "SELECT id, long_name, short_name FROM route WHERE active is true";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);

			while (rs.next()) {

				long id = rs.getLong("id");
				String longName = rs.getString("long_name");
				String shortName = rs.getString("short_name");
				Route route = new Route(id, longName, shortName, Route.RouteType.BUS);
				result.add(route);
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
