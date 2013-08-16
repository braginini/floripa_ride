package com.floriparide.mobfloripaparser.model;

/**
 * Created by Mikhail Bragin
 */
public class OSMStop implements Comparable<OSMStop> {

	String name;

	Long id;

	Double lat;

	Double lon;

	Long sequence;

	public OSMStop(Long id, String name, Double lat, Double lon) {
		this.name = name;
		this.id = id;
		this.lat = lat;
		this.lon = lon;
	}

	public OSMStop(Long id, String name, Double lat, Double lon, Long sequence) {
		this.name = name;
		this.id = id;
		this.lat = lat;
		this.lon = lon;
		this.sequence = sequence;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	@Override
	public int compareTo(OSMStop o) {

		if (this.sequence < o.sequence)
			return -1;
		else if (this.sequence > o.sequence)
			return 1;

		return 0;
	}
}
