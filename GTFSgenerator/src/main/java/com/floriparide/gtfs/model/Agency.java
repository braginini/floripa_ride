package com.floriparide.gtfs.model;

/**
 * @author mikhail.bragin
 * @since 8/11/13
 */
public class Agency {

    Long id;

    String name;

    String url;

	String timezone;

	String phone;

	String fareUrl;

    public Agency(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Agency(String name, String url) {
        this.name = name;
        this.url = url;
    }

	public Agency(Long id, String name, String url, String timezone) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.timezone = timezone;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFareUrl() {
		return fareUrl;
	}

	public void setFareUrl(String fareUrl) {
		this.fareUrl = fareUrl;
	}
}
