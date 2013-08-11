package com.floriparide.mobfloripaparser.model;

/**
 * @author mikhail.bragin
 * @since 8/11/13
 */
public class Agency {

    Long id;

    String name;

    String url;

    public Agency(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Agency(String name, String url) {
        this.name = name;
        this.url = url;
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
}
