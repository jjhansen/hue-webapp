package com.electromagneticsoftware.business.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class Groups implements Serializable {

	private static final long serialVersionUID = 1L;
	private Map<String,Object> data = new HashMap<String,Object>();
	private final ObjectMapper objectMapper = new ObjectMapper();
//	private Map<String,Light> lights;

	public List<Group> getGroups() {
		List<Group> list = new ArrayList<Group>();
		for ( String key : data.keySet() ) {
			Object value = data.get(key);
			Group group = objectMapper.convertValue( value, Group.class );
			group.setId(key);
			list.add( group );
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
