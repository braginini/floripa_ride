package com.floriparide.osm;

/**
 * Created by Mike on 12/24/13.
 */
public class MatchParser {

    public static final String SEPARATOR = ",";

    public static Match parse(String line) {
        String[] split = line.split(SEPARATOR);
        if (split.length < 3)
            throw new IllegalArgumentException("Wrong line " + line);

        Long routeId;
        Long osmTo;
        Long osmReturn;
        try {
            routeId = Long.parseLong(split[0]);
            osmTo = (!split[1].isEmpty()) ? Long.parseLong(split[1]) : null;
            osmReturn = (!split[2].isEmpty()) ? Long.parseLong(split[2]) : null;

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input " + line, e);
        }

        if (osmTo == null && osmReturn == null)
            throw new IllegalArgumentException("Invalid input. Should be at least one osm reference " + line);

        return new Match(routeId, osmTo, osmReturn);
    }


}
