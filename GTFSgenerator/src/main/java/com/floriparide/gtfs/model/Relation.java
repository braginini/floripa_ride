package com.floriparide.gtfs.model;

import java.util.List;
import java.util.Map;

/**
 * Created by Mikhail Bragin
 */
public class Relation extends AbstractNode {

	private List<Member> members;

	public Relation(Long id, String visible, String timestamp, String version, String changeset, String user, String uid, Map<String, String> tags, List<Member> members) {
		super(id, visible, timestamp, version, changeset, user, uid, tags);
		this.members = members;
	}

	public Relation(Long id, Map<String, String> tags, List<Member> members) {
		super(id, tags);
		this.members = members;
	}

	public Relation(Long id, Map<String, String> tags) {
		super(id, tags);
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}
}
