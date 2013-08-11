package com.floriparide.mobfloripaparser.model;

/**
 * @author mikhail.bragin
 * @since 8/11/13
 */
public interface ParseResult<T> {

    T result();

    void setResult(T result);
}
