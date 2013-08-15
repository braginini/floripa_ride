package com.floriparide.mobfloripaparser.dao;

import com.floriparide.mobfloripaparser.model.Agency;
import com.floriparide.mobfloripaparser.model.Route;
import com.floriparide.mobfloripaparser.model.Trip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
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
		String SQL = "INSERT INTO trip (route_id, service_id, trip_time, start_time) " +
				"VALUES (?, ?, ?, ?)";
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
				stmt.addBatch();
			}

			int[] rs = stmt.executeBatch();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceKeeper.closeConnection(connection);
		}
	}
}
