package com.floriparide.mobfloripaparser.model;

/**
 * Created by Mikhail Bragin
 */
public class Trip {

	Long id;

	Long routeId;

	Long serviceId;

	Long shapeId;

	public Trip(Long id, Long routeId, Long serviceId, Long shapeId) {
		this.id = id;
		this.routeId = routeId;
		this.serviceId = serviceId;
		this.shapeId = shapeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Long getShapeId() {
		return shapeId;
	}

	public void setShapeId(Long shapeId) {
		this.shapeId = shapeId;
	}
}
