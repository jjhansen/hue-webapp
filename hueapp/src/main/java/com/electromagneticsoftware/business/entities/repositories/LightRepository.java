package com.electromagneticsoftware.business.entities.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.electromagneticsoftware.business.entities.Bridge;
import com.electromagneticsoftware.business.entities.Light;
import com.electromagneticsoftware.business.entities.Lights;

@Repository
public class LightRepository {

	List<Light> lights;
	
	@Autowired
	RestTemplate restTemplate;
	
	public List<Light> findAll( Bridge bridge ) {
		String url = "http://" + bridge.getIpAddress() + "/api/" + bridge.getUsername() + "/lights";
		Lights myLights = restTemplate.getForObject( url , Lights.class);
		lights = myLights.getLights();
		return lights;
	}

	public Light findLight( Long id, Bridge bridge ) {
		String url = "http://" + bridge.getIpAddress() + "/api/" + bridge.getUsername() + "/lights/" + id;
		Light l = restTemplate.getForObject( url , Light.class);
		return l;
	}
}
