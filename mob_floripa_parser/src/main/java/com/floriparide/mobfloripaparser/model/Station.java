package com.floriparide.mobfloripaparser.model;

/**
 * @author mikhail.bragin
 * @since 8/11/13
 */
public class Station {

    Long id;

    String name;

    public Station(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Station(String name) {
        this.name = name;
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
}