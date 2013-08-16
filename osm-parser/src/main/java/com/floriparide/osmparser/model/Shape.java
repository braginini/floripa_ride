package com.floriparide.osmparser.model;

/**
 * Created by Mikhail Bragin
 */
public class Shape implements Comparable<Shape> {

	Double lat;
	Double lon;
	long sequence;
	long routeId;

	public Shape(Double lat, Double lon, long sequence) {
		this.lat = lat;
		this.lon = lon;
		this.sequence = sequence;
	}

	public Shape(Double lat, Double lon, long sequence, long routeId) {
		this.lat = lat;
		this.lon = lon;
		this.sequence = sequence;
		this.routeId = routeId;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public long getSequence() {
		return sequence;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}

	public long getRouteId() {
		return routeId;
	}

	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}

	@Override
	public int compareTo(Shape o) {

		if (this.sequence < o.sequence)
			return -1;
		else if (this.sequence > o.sequence)
			return 1;

		return 0;
	}
}
