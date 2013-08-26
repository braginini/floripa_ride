package com.floriparide.gtfs.dao;

import com.floriparide.gtfs.model.Trip;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikhail Bragin
 */
public class TripDao extends Dao {

	public TripDao(DataSourceKeeper dataSourceKeeper) {
		super(dataSourceKeeper);
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
}
