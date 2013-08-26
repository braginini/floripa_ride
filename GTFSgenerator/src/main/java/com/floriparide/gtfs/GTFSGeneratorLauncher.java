package com.floriparide.gtfs;

import com.floriparide.gtfs.dao.DataSourceKeeper;
import com.floriparide.gtfs.dao.NodeDao;
import com.floriparide.gtfs.dao.RelationDao;
import com.floriparide.gtfs.dao.RouteDao;
import com.floriparide.gtfs.dao.TripDao;
import com.floriparide.gtfs.dao.WayDao;
import com.floriparide.gtfs.writer.AbstractGTFSFileWriter;
import com.floriparide.gtfs.writer.RoutesWriter;
import com.floriparide.gtfs.writer.ShapesWriter;
import com.floriparide.gtfs.writer.StopTimesWriter;
import com.floriparide.gtfs.writer.StopsWriter;
import com.floriparide.gtfs.writer.TripsWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikhail Bragin
 */
public class GTFSGeneratorLauncher {

	public static void main(String[] args) throws Exception {

		for (AbstractGTFSFileWriter writer : getWriters()) {
			writer.write();
		}

	}

	private static List<AbstractGTFSFileWriter> getWriters() throws Exception {

		List<AbstractGTFSFileWriter> writers = new ArrayList<>();

		DataSourceKeeper osmDataSource = new DataSourceKeeper("floripa_osm_data");
		NodeDao nodeDao = new NodeDao(osmDataSource);
		WayDao wayDao = new WayDao(osmDataSource);
		RelationDao relationDao = new RelationDao(osmDataSource);

		writers.add(new StopsWriter(nodeDao, wayDao));

		writers.add(new ShapesWriter(relationDao, wayDao, nodeDao));

		DataSourceKeeper timeTableDataSource = new DataSourceKeeper("floripa_timetable_data");
		RouteDao routeDao = new RouteDao(timeTableDataSource);
		TripDao tripDao = new TripDao(timeTableDataSource);

		writers.add(new RoutesWriter(routeDao));

		writers.add(new TripsWriter(tripDao));

		writers.add(new StopTimesWriter(tripDao, nodeDao));

		return writers;
	}
}
