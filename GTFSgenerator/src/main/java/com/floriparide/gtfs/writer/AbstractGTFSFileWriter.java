package com.floriparide.gtfs.writer;

import com.floriparide.gtfs.dao.Dao;
import com.floriparide.gtfs.model.Node;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Mikhail Bragin
 */
public abstract class AbstractGTFSFileWriter<T> {

	public static final String DELIMETER = ",";

	protected abstract void writeContents();

    protected abstract void writeHeading() throws FileNotFoundException, UnsupportedEncodingException;

	public void write() throws FileNotFoundException, UnsupportedEncodingException {
		writeHeading();
		writeContents();
	}

    protected abstract String getLine(T obj);
}
