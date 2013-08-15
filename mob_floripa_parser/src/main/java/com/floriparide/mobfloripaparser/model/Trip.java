package com.floriparide.mobfloripaparser.model;

/**
 * Created by Mikhail Bragin
 */
public class Trip {

	Long id;

	Long routeId;

	Long serviceId;

	Long shapeId;

	Long calendarId;

	Calendar calendar;

	Float length;  //km

	Integer tripTime;  //min

	Float priceCard;

	Float price;

	TripDirection direction;

	String startTime;

	public Trip(Long id, Long routeId, Long serviceId, Long shapeId) {
		this.id = id;
		this.routeId = routeId;
		this.serviceId = serviceId;
		this.shapeId = shapeId;
	}

	public Trip(Long routeId,
	            Long calendarId,
	            Float length,
	            Integer tripTime,
	            Float priceCard,
	            Float price,
	            TripDirection direction,
	            String startTime) {
		this.routeId = routeId;
		this.calendarId = calendarId;
		this.length = length;
		this.tripTime = tripTime;
		this.priceCard = priceCard;
		this.price = price;
		this.direction = direction;
		this.startTime = startTime;
	}

	public Trip(Long routeId,
	            Long calendarId,
	            TripDirection direction,
	            String startTime) {
		this.routeId = routeId;
		this.calendarId = calendarId;
		this.direction = direction;
		this.startTime = startTime;
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

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public Float getLength() {
		return length;
	}

	public void setLength(Float length) {
		this.length = length;
	}

	public Integer getTripTime() {
		return tripTime;
	}

	public void setTripTime(Integer tripTime) {
		this.tripTime = tripTime;
	}

	public Float getPriceCard() {
		return priceCard;
	}

	public void setPriceCard(Float priceCard) {
		this.priceCard = priceCard;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public TripDirection getDirection() {
		return direction;
	}

	public void setDirection(TripDirection direction) {
		this.direction = direction;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Long getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(Long calendarId) {
		this.calendarId = calendarId;
	}

	public enum TripDirection {

		OUTBOUND(false),
		INBOUND(true);

		boolean id;

		private TripDirection(boolean id) {
			this.id = id;
		}

		public boolean getId() {
			return id;
		}

		public void setId(boolean id) {
			this.id = id;
		}
	}
}
