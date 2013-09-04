package com.floriparide.gtfs.writer;

import com.floriparide.gtfs.dao.AgencyDao;
import com.floriparide.gtfs.model.Agency;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Mikhail Bragin
 */
public class AgensiesWriter extends AbstractGTFSFileWriter<Agency> {

	AgencyDao agencyDao;

	public AgensiesWriter(AgencyDao agencyDao) {
		this.agencyDao = agencyDao;
	}

	@Override
	protected void writeContents() {
	}

	@Override
	protected void writeHeading() throws FileNotFoundException, UnsupportedEncodingException {
	}

	@Override
	protected String getLine(Agency obj) {
		return null;
	}
}
