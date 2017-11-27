package com.electromagneticsoftware.business.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Light implements Serializable, Comparable<Light> {
	private static final long serialVersionUID = 1L;

	private String id;
	private LightState state;
	private String type;
	private String name;
	private String modelid;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LightState getState() {
		return state;
	}
	public void setState(LightState state) {
		this.state = state;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModelid() {
		return modelid;
	}
	public void setModelid(String modelid) {
		this.modelid = modelid;
	}
	@Override
	public int compareTo(Light o) {		
		return Integer.valueOf(id).compareTo(Integer.valueOf(o.id));
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Light other = (Light) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
