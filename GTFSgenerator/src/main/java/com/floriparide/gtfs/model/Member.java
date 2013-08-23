package com.floriparide.gtfs.model;

/**
 * Created by Mikhail Bragin
 */
public class Member {

	private java.lang.String type;

	private java.lang.Long id;

	private java.lang.String role;

	private Integer sequenceId;

	private Long relationId;

	public Member(String type, Long ref, String role, Integer sequenceId, Long relationId) {
		this.type = type;
		this.id = ref;
		this.role = role;
		this.sequenceId = sequenceId;
		this.relationId = relationId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(Integer sequenceId) {
		this.sequenceId = sequenceId;
	}

	public Long getRelationId() {
		return relationId;
	}

	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}

	public enum MemberType {
		W, N
	}
}
