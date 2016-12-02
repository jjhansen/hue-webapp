package com.electromagneticsoftware.business.entities.repositories;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.electromagneticsoftware.business.entities.Bridge;
import com.electromagneticsoftware.business.entities.Light;
import com.electromagneticsoftware.business.entities.Lights;


public class LightRepositoryImpl implements LightRepository {

	List<Light> lights;
	
	@Autowired
	RestTemplate restTemplate;
	
	public LightRepositoryImpl() {
	}
	
	@Override
	public List<Light> findAll( Bridge bridge ) {
		String url = "http://" + bridge.getIpAddress() + "/api/" + bridge.getUsername() + "/lights";
		Lights myLights = restTemplate.getForObject( url , Lights.class);
		lights = myLights.getLights();
		return lights;
	}

	@Override
	public Light findLight( Long id, Bridge bridge ) {
		String url = "http://" + bridge.getIpAddress() + "/api/" + bridge.getUsername() + "/lights/" + id;
		Light l = restTemplate.getForObject( url , Light.class);
		return l;
	}

}
