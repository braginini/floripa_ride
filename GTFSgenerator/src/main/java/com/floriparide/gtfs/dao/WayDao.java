package com.floriparide.gtfs.dao;

import com.floriparide.gtfs.model.Relation;
import com.floriparide.gtfs.model.Way;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mikhail Bragin
 */
public class WayDao extends Dao {

	NodeDao nodeDao;

	public WayDao(DataSourceKeeper dataSourceKeeper, NodeDao nodeDao) {
		super(dataSourceKeeper);
		this.nodeDao = nodeDao;
	}

	public List<Way> getWays(List<Long> ids) {

		List<Way> ways = new ArrayList<>();

		Connection con = dataSourceKeeper.getConnection();

		try {

			String in = getInOperator(ids);

			String SQL = "SELECT id FROM ways WHERE id IN " + in;

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);

			while (rs.next()) {

				long id = rs.getLong("id");
			    ways.add(new Way(id, new HashMap<String, String>()));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		} finally {
			DataSourceKeeper.closeConnection(con);
		}

		for (Way way : ways) {
			way.setNodes(nodeDao.getNodesByWayOrdered(way.getId()));
		}

		return ways;
	}

	private String getInOperator(List<Long> ids) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (Long id : ids) {
			sb.append(id);
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		return sb.toString();
	}

}
