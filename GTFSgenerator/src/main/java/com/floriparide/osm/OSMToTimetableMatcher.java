package com.floriparide.osm;

import com.floriparide.gtfs.dao.DataSourceKeeper;
import com.floriparide.gtfs.dao.MatchDao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Matches OSM routes to timetable
 * Created by Mike on 12/24/13.
 */
public class OSMToTimetableMatcher {

    public static void main(String[] args) throws Exception {

        if (args.length < 1)
            throw new IllegalArgumentException("Missing arguments");

        BufferedReader reader = new BufferedReader(new FileReader(args[0]));

        DataSourceKeeper timeTableDataSource = new DataSourceKeeper("floripa_timetable_data");
        MatchDao dao = new MatchDao(timeTableDataSource);

        List<Match> matches = new ArrayList<>();
        String line = reader.readLine(); //skip header
        while ((line = reader.readLine()) != null) {
            matches.add(MatchParser.parse(line));
        }

        for (Match match : matches) {
            dao.matchOSMToTimeTable(match);
        }


    }
}
