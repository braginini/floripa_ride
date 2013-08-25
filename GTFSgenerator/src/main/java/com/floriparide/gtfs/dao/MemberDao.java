package com.floriparide.gtfs.dao;

import com.floriparide.gtfs.model.Member;
import com.floriparide.gtfs.model.Relation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikhail Bragin
 */
public class MemberDao extends Dao {

	public MemberDao(DataSourceKeeper dataSourceKeeper) {
		super(dataSourceKeeper);
	}

	List<Member> getRelationMembersWithTypeOrdered(long relationId, Member.MemberType type) {

		List<Member> relations = new ArrayList<>();

		Connection con = dataSourceKeeper.getConnection();

		try {

			String SQL = "SELECT t.member_id, t.member_role, t.sequence_id " +
					"FROM relation_members t " +
					"WHERE t.relation_id =" + relationId + " " +
					"AND member_type = '" + type.name() + "' " +
					"ORDER BY sequence_id";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);

			while (rs.next()) {

				long id = rs.getLong("member_id");
				String role = rs.getString("member_role");
				int sequenceId = rs.getInt("sequence_id");

				relations.add(new Member(type.name(), id, role, sequenceId, relationId));
			}

		} catch (Exception e) {
            e.printStackTrace();
			System.exit(0);
		} finally {
			DataSourceKeeper.closeConnection(con);
		}

		return relations;
	}

	List<Member> getRelationWayMembersOrdered(long relationId) {
		return getRelationMembersWithTypeOrdered(relationId, Member.MemberType.W);
	}
}
