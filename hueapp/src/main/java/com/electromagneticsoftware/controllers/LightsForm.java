package com.electromagneticsoftware.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.electromagneticsoftware.business.entities.Light;


public class LightsForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<FormLight> lights = new ArrayList<FormLight>();

	public LightsForm() {
	}
	
	public LightsForm(List<Light> lights2) {
		for (Light light : lights2) {
			FormLight f = new FormLight();
			f.setLight(light);
			f.setSelected(false);
			f.setId(light.getId());
			lights.add(f);
		}
	}

	public List<FormLight> getLights() {
		return lights;
	}

	public void setLights(List<FormLight> lights) {
		this.lights = lights;
	}
}
