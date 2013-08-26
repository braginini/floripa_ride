package com.floriparide.gtfs.model.osm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mikhail Bragin
 */
public class Node extends AbstractNode {

	private Double lat;
	private Double lon;

	public Node(Long id, String visible, String timestamp, String version, String changeset, String user, String uid, Map<String, String> tags, Double lat, Double lon) {
		super(id, visible, timestamp, version, changeset, user, uid, tags);
		this.lat = lat;
		this.lon = lon;
	}

	public Node(Long id, Map<String, String> tags, Double lat, Double lon) {
		super(id, tags);
		this.lat = lat;
		this.lon = lon;
	}

	public Node(Long id) {
		super(id, new HashMap<String, String>());
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
