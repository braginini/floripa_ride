package com.floriparide.gtfs.writer;

import com.floriparide.gtfs.dao.Dao;
import com.floriparide.gtfs.model.Node;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by Mikhail Bragin
 */
public class ShapesWriter extends AbstractGTFSFileWriter<Node> {


	protected ShapesWriter(Dao dao) {

	}

	@Override
	public void writeContents() {
	}

	@Override
	public void writeHeading() throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("shapes.txt", "UTF-8");

		StringBuilder sb = new StringBuilder()
				.append("shape_id")
				.append(DELIMETER)
				.append("shape_pt_lat")
				.append(DELIMETER)
				.append("shape_pt_lon")
				.append(DELIMETER)
				.append("shape_pt_sequence")
				.append(DELIMETER)
				.append("shape_dist_traveled");

		writer.println(sb.toString());
		writer.close();
	}

	@Override
	String getLine(Node obj) {
		return null;
	}
}
