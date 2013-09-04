package com.floriparide.gtfs.dao;

import com.floriparide.gtfs.model.Agency;
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
public class AgencyDao extends Dao {

	public AgencyDao(DataSourceKeeper dataSourceKeeper) {
		super(dataSourceKeeper);
	}

	public List<Agency> getAllAgencies() {

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
						rs.getString("url"),
						rs.getString("timezone")));
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


}
