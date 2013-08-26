package com.floriparide.gtfs.model.osm;

import java.util.Map;

/**
 * Created by Mikhail Bragin
 */
public abstract class AbstractNode {

	private java.lang.Long id;
	private java.lang.String visible;
	private java.lang.String timestamp;
	private java.lang.String version;
	private java.lang.String changeset;
	private java.lang.String user;
	private java.lang.String uid;
	private java.util.Map<java.lang.String,java.lang.String> tags;

	protected AbstractNode(Long id, String visible, String timestamp, String version, String changeset, String user, String uid, Map<String, String> tags) {
		this.id = id;
		this.visible = visible;
		this.timestamp = timestamp;
		this.version = version;
		this.changeset = changeset;
		this.user = user;
		this.uid = uid;
		this.tags = tags;
	}

	protected AbstractNode(Long id, Map<String, String> tags) {
		this.id = id;
		this.tags = tags;
	}

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getChangeset() {
		return changeset;
	}

	public void setChangeset(String changeset) {
		this.changeset = changeset;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Map<String, String> getTags() {
		return tags;
	}

	public void setTags(Map<String, String> tags) {
		this.tags = tags;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractNode that = (AbstractNode) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
