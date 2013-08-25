package com.floriparide.gtfs.writer;

import com.floriparide.gtfs.dao.NodeDao;
import com.floriparide.gtfs.model.Node;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Mikhail Bragin
 */
public class StopsWriter extends AbstractGTFSFileWriter<Node> {

	NodeDao dao;

	public StopsWriter(NodeDao dao) {
		this.dao = dao;
	}

	@Override
    protected void writeContents() {
		List<Node> stops = dao.getNodesWithTag("highway", "bus_stop");

		if (stops.isEmpty())
			return;

		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter("stops.txt", true)));

			for (Node node : stops) {
				 writer.println(getLine(node));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}

	@Override
    protected void writeHeading() throws FileNotFoundException, UnsupportedEncodingException {

		PrintWriter writer = new PrintWriter("stops.txt", "UTF-8");

		StringBuilder sb = new StringBuilder()
				.append("stop_id")
				.append(DELIMETER)
				.append("stop_code")
				.append(DELIMETER)
				.append("stop_name")
				.append(DELIMETER)
				.append("stop_desc")
				.append(DELIMETER)
				.append("stop_lat")
				.append(DELIMETER)
				.append("stop_lon")
				.append(DELIMETER)
				.append("zone_id")
				.append(DELIMETER)
				.append("stop_url")
				.append(DELIMETER)
				.append("location_type")
				.append(DELIMETER)
				.append("direction")
				.append(DELIMETER)
				.append("position");

		writer.println(sb.toString());
		writer.close();
	}

	@Override
    protected String getLine(Node node) {

		StringBuilder sb = new StringBuilder()
				.append(node.getId())
				.append(DELIMETER)
				.append(DELIMETER)
				.append((node.getTags().containsKey("name")) ? node.getTags().get("name") : node.getId())
				.append(DELIMETER)
				.append(DELIMETER)
				.append(node.getLat())
				.append(DELIMETER)
				.append(node.getLon())
				.append(DELIMETER)
				.append(DELIMETER)
				.append(DELIMETER)
				.append(DELIMETER)
				.append(DELIMETER);

		return sb.toString();
	}
}
