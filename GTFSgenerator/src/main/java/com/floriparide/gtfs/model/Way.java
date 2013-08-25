package com.floriparide.gtfs.model;

import java.util.List;
import java.util.Map;

/**
 * Created by Mikhail Bragin
 */
public class Way extends AbstractNode {

	public static final java.lang.String HIGHWAY = "highway";
	private List<Node> nodes;

	public Way(Long id, String visible, String timestamp, String version, String changeset, String user, String uid, Map<String, String> tags, List<Node> nodes) {
		super(id, visible, timestamp, version, changeset, user, uid, tags);
		this.nodes = nodes;
	}

	public Way(Long id, List<Node> nodes, Map<String, String> tags) {
		super(id, tags);
		this.nodes = nodes;
	}

	public Way(Long id, Map<String, String> tags) {
		super(id, tags);
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

    public enum Intersection {

        LAST_FIRST, LAST_LAST, FIRST_LAST, FIRST_FIRST, NONE
    }
}
