package com.electromagneticsoftware.business.entities.repositories;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.electromagneticsoftware.business.entities.Light;
import com.electromagneticsoftware.business.entities.Lights;


public class LightRepositoryImpl implements LightRepository {

	List<Light> lights;
	String username;
	String ip;
	
	@Autowired
	RestTemplate restTemplate;
	
	public LightRepositoryImpl() {
		username = "3f451a9f534fab0714d7b6bc27208ba3";
		ip = "192.168.2.111";
	}
	
	@Override
	public List<Light> findAll() {
		String url = "http://" + ip + "/api/" + username + "/lights";
		Lights myLights = restTemplate.getForObject( url , Lights.class);
		lights = myLights.getLights();
		return lights;
	}

	@Override
	public Light findLight(Long id) {
		String url = "http://" + ip + "/api/" + username + "/lights/" + id;
		Light l = restTemplate.getForObject( url , Light.class);
		return l;
	}

	@Override
	public String getIp() {
		return ip;
	}

	@Override
	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}


}
