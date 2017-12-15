package com.electromagneticsoftware.business.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Group implements Serializable, Comparable<Group> {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String type;
	private String[] lights;
	
	@Override
	public int compareTo(Group o) {
		// TODO Auto-generated method stub
		return name.compareTo(o.name);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getLights() {
		return lights;
	}

	public void setLights(String[] lights) {
		this.lights = lights;
	}

}
