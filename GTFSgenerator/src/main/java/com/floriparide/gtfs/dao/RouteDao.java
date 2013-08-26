package com.floriparide.gtfs.dao;

import com.floriparide.gtfs.model.Route;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikhail Bragin
 */
public class RouteDao extends Dao {

	public RouteDao(DataSourceKeeper dataSourceKeeper) {
		super(dataSourceKeeper);
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
