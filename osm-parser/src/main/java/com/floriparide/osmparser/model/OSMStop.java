package com.floriparide.osmparser.model;

/**
 * Created by Mikhail Bragin
 */
public class OSMStop {

	String name;

	Long id;

	Double lat;

	Double lon;

	public OSMStop(Long id, String name,  Double lat, Double lon) {
		this.name = name;
		this.id = id;
		this.lat = lat;
		this.lon = lon;
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
}
