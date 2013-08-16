package com.floriparide.osmparser.model;

import java.util.List;

import br.zuq.osm.parser.model.OSMNode;
import br.zuq.osm.parser.model.Relation;

/**
 * Created by Mikhail Bragin
 */
public class OSMRoute {

	Relation relation;

	List<OSMNode> shape;

	List<OSMNode> stops;


	public OSMRoute(Relation relation, List<OSMNode> shape, List<OSMNode> stops) {
		this.relation = relation;
		this.shape = shape;
		this.stops = stops;
	}

	public OSMRoute(Relation relation) {
		this.relation = relation;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public List<OSMNode> getShape() {
		return shape;
	}

	public void setShape(List<OSMNode> shape) {
		this.shape = shape;
	}

	public List<OSMNode> getStops() {
		return stops;
	}

	public void setStops(List<OSMNode> stops) {
		this.stops = stops;
	}
}
