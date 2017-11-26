package com.electromagneticsoftware.business.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationLogin implements Serializable {
	private static final long serialVersionUID = 1L;
	// TODO: add computer name to device type
	private String devicetype = "electromagneticsoftware#hue app";

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}
}
