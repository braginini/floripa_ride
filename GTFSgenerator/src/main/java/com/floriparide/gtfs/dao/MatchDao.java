package com.floriparide.gtfs.dao;

import com.floriparide.osm.Match;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Mike on 12/24/13.
 */
public class MatchDao extends Dao {

    public MatchDao(DataSourceKeeper dataSourceKeeper) {
        super(dataSourceKeeper);
    }


    public void matchOSMToTimeTable(Match match) throws Exception {
        String SQL = "UPDATE route SET active = TRUE WHERE id = ?;";
        Connection connection = null;

        try {
            connection = dataSourceKeeper.getConnection();

            PreparedStatement stmt = connection.prepareStatement(SQL);
            stmt.setLong(1, match.getRouTeId());
            stmt.execute();

            if (match.getOsmTo() != null) {
                SQL = "UPDATE trip SET osm_route_id = ? WHERE direction is true and route_id = ?;";
                stmt = connection.prepareCall(SQL);
                stmt.setLong(1, match.getOsmTo());
                stmt.setLong(2, match.getRouTeId());
                stmt.execute();
            }

            if (match.getOsmReturn() != null) {
                SQL = "UPDATE trip SET osm_route_id = ? WHERE direction is false and route_id = ?;";
                stmt = connection.prepareCall(SQL);
                stmt.setLong(1, match.getOsmReturn());
                stmt.setLong(2, match.getRouTeId());
                stmt.execute();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceKeeper.closeConnection(connection);
        }

    }
}
