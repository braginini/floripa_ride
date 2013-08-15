package com.floriparide.mobfloripaparser.model;

/**
 * @author mikhail.bragin
 * @since 8/11/13
 */
public class Route {

    Long id;

    String longName;

    Long agencyId;

    String shortName;

    String description;

    RouteType type;

    Station origin;

    Station destination;

    String parseUrl;

    Agency agency;

	public Route(Long id, String shortName, String parseUrl) {
		this.id = id;
		this.parseUrl = parseUrl;
		this.shortName = shortName;
	}

	/**
     * Use this constructor to create new route
     *
     */
    public Route(String longName, String shortName, RouteType type) {
        this.longName = longName;
        this.type = type;
        this.shortName = shortName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public Long getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Long agencyId) {
        this.agencyId = agencyId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RouteType getType() {
        return type;
    }

    public void setType(RouteType type) {
        this.type = type;
    }

    public Station getOrigin() {
        return origin;
    }

    public void setOrigin(Station origin) {
        this.origin = origin;
    }

    public Station getDestination() {
        return destination;
    }

    public void setDestination(Station destination) {
        this.destination = destination;
    }

    public String getParseUrl() {
        return parseUrl;
    }

    public void setParseUrl(String parseUrl) {
        this.parseUrl = parseUrl;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public enum RouteType {
        TRAM,
        SUBWAY,
        RAIL,
        BUS,
        FERRY,
        CABLE,
        GONDOLA,
        FUNICULAR
    }
}
