package com.floriparide.osm;

/**
 * Created by Mike on 12/24/13.
 */
public class Match {
    long rouTeId;
    Long osmTo;
    Long osmReturn;

    public Match(long rouTeId, Long osmTo, Long osmReturn) {
        this.rouTeId = rouTeId;
        this.osmTo = osmTo;
        this.osmReturn = osmReturn;
    }

    public Long getOsmReturn() {
        return osmReturn;
    }

    public Long getOsmTo() {
        return osmTo;
    }

    public long getRouTeId() {
        return rouTeId;
    }
}