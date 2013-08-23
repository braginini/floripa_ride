package com.floriparide.gtfs.dao;

/**
 * Created by Mikhail Bragin
 */
public abstract class Dao {

	DataSourceKeeper dataSourceKeeper;

	protected Dao(DataSourceKeeper dataSourceKeeper) {
		this.dataSourceKeeper = dataSourceKeeper;
	}
}
