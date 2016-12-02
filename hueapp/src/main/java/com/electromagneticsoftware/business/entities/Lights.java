package com.electromagneticsoftware.business.entities;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class Lights {
	private Map<String,Object> data = new HashMap<String,Object>();
	private ObjectMapper objectMapper = new ObjectMapper();
//	private Map<String,Light> lights;

	public List<Light> getLights() {
		List<Light> list = new ArrayList<Light>();
		for ( Object value : data.values() ) {
			Light light = objectMapper.convertValue( value, Light.class );
			list.add( light );
		}
		return list;
	}

//	public void setLights(Map<String, Light> lights) {
//		this.lights = lights;
//	}
//
	@JsonAnySetter
	private void handleUnknown( String property, Object value ) {
		data.put( property, value);
	}
}
