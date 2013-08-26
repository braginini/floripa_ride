package com.floriparide.gtfs.dao;

import com.floriparide.gtfs.model.osm.Relation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mikhail Bragin
 */
public class RelationDao extends Dao {

	public RelationDao(DataSourceKeeper dataSourceKeeper) {
		super(dataSourceKeeper);
	}

	/**
	 * Returns {@link Relation} objects containing only id field
	 *
	 * @param tagKey
	 * @param tagValue
	 * @return
	 */
	public List<Relation> getRelationsWithTagSimple(String tagKey, String tagValue) {

		List<Relation> relations = new ArrayList<>();

		Connection con = dataSourceKeeper.getConnection();

		try {

			String SQL = "SELECT r.id FROM relations r, relation_tags t " +
					"WHERE r.id = t.relation_id " +
                    "AND r.id IN (2521697, 2521698, 2082735, 2168842, 2236714, 2236713, 2089834, 2089835) " +
					"AND t.k = '" + tagKey +"' " +
					"AND t.v = '" + tagValue +"' " +
					"GROUP BY r.id";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);

			while (rs.next()) {

				long id = rs.getLong("id");
				relations.add(new Relation(id, new HashMap<String, String>()));
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		} finally {
			DataSourceKeeper.closeConnection(con);
		}

		return relations;
	}
}
