package com.floriparide.gtfs.dao;

import com.floriparide.gtfs.model.osm.Way;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

/**
 * Created by Mikhail Bragin
 */
public class WayDao extends Dao {

	public WayDao(DataSourceKeeper dataSourceKeeper) {
		super(dataSourceKeeper);
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
            e.printStackTrace();
			System.exit(0);
		} finally {
			DataSourceKeeper.closeConnection(con);
		}

		return ways;
	}

    public List<Way> getWaysInRelationOrdered(long relationId) {

        List<Way> ways = new ArrayList<>();

        Connection con = dataSourceKeeper.getConnection();

        try {

            String SQL = "SELECT w.id, m.sequence_id FROM ways w " +
                    "JOIN relation_members m on w.id = m.member_id " +
                    "AND m.relation_id = " + relationId + " " +
                    "AND m.member_type = 'W' " +
                    "AND m.member_role != 'platform' "  +
                    "ORDER BY sequence_id ";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {

                long id = rs.getLong("id");
                ways.add(new Way(id, new HashMap<String, String>()));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        } finally {
            DataSourceKeeper.closeConnection(con);
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

    public List<Way> getWaysWithTag(String tagKey, String tagValue) {
        List<Way> ways = new ArrayList<>();

        Connection con = dataSourceKeeper.getConnection();
        try {

            String SQL = "SELECT w.id, w.bbox FROM ways w, way_tags t " +
                    "WHERE w.id = t.way_id " +
                    "AND t.k = '" + tagKey + "' " +
                    "AND t.v = '" + tagValue + "' " +
                    "GROUP BY w.id";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {

                long id = rs.getLong("id");

                ways.add(new Way(id, new HashMap<String, String>()));

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        } finally {
            DataSourceKeeper.closeConnection(con);
        }

        for (Way way : ways) {
            way.setTags(getWayTags(way.getId()));
        }

        return ways;
    }

    private Map<String, String> getWayTags(long wayId) {
        Connection con = dataSourceKeeper.getConnection();
        try {

            String SQL = "SELECT * from way_tags WHERE way_id = " + wayId;
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
}
