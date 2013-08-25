package com.floriparide.gtfs.model;

/**
 * Created with IntelliJ IDEA.
 * User: Mike
 * Date: 8/25/13
 * Time: 4:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Shape {

    double lat;

    double lon;

    int sequenceId;

    long routeId;

    public Shape(double lat, double lon, int sequenceId, long routeId) {
        this.lat = lat;
        this.lon = lon;
        this.sequenceId = sequenceId;
        this.routeId = routeId;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(int sequenceId) {
        this.sequenceId = sequenceId;
    }

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }
}
