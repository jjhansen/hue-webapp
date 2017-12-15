package com.electromagneticsoftware.controllers;

import java.io.Serializable;

import com.electromagneticsoftware.business.entities.Light;

public class FormLight implements Serializable, Comparable<FormLight>{
	private static final long serialVersionUID = 1L;
	private Boolean selected;
	private String id;
	private Light light;
		
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
		FormLight other = (FormLight) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(FormLight arg0) {
		return light.compareTo(arg0.light);
	}
	
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Light getLight() {
		return light;
	}
	public void setLight(Light light) {
		this.light = light;
	}
}
