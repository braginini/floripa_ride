package com.floriparide.gtfs;

import com.floriparide.gtfs.dao.DataSourceKeeper;
import com.floriparide.gtfs.dao.NodeDao;
import com.floriparide.gtfs.dao.RelationDao;
import com.floriparide.gtfs.dao.WayDao;
import com.floriparide.gtfs.writer.AbstractGTFSFileWriter;
import com.floriparide.gtfs.writer.ShapesWriter;
import com.floriparide.gtfs.writer.StopsWriter;

/**
 * Created by Mikhail Bragin
 */
public class GTFSGeneratorLauncher {

    public static void main(String[] args) throws Exception {

        DataSourceKeeper osmFloripaDataSource = new DataSourceKeeper("floripa_osm_data");

        AbstractGTFSFileWriter writer = new StopsWriter(new NodeDao(osmFloripaDataSource));

        writer.write();

        writer = new ShapesWriter(
                new RelationDao(osmFloripaDataSource),
                new WayDao(osmFloripaDataSource),
                new NodeDao(osmFloripaDataSource));

        writer.write();
    }
}
