package com.electromagneticsoftware.business.entities.repositories;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.electromagneticsoftware.business.entities.Light;
import com.electromagneticsoftware.business.entities.LightStateUpdate;
import com.electromagneticsoftware.business.entities.Lights;
import com.electromagneticsoftware.services.BridgeProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class LightRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(LightRepository.class);
	private final ObjectMapper objectMapper = new ObjectMapper();
	List<Light> lights;
	
	@Autowired
	RestTemplate restTemplate;
	
	public List<Light> findAll(BridgeProperties bridge) {
		String url = "http://" + bridge.getBridgeIp() + "/api/" + bridge.getUsername() + "/lights";
		Lights myLights = restTemplate.getForObject(url , Lights.class);
		lights = myLights.getLights();
		return lights;
	}

	public Light findLight(Long id, BridgeProperties bridge) {
		String url = "http://" + bridge.getBridgeIp() + "/api/" + bridge.getUsername() + "/lights/" + id;
		Light l = restTemplate.getForObject(url , Light.class);
		return l;
	}
	
	public void setState(BridgeProperties bridge, List<String> ids, LightStateUpdate state) throws JsonProcessingException {
		String url = "http://" + bridge.getBridgeIp() + "/api/" + bridge.getUsername() + "/lights/{id}/state";
		String requestBody = objectMapper.writeValueAsString(state);
		for (String id : ids) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class, id);
			String responseBody = response.getBody();
			LOGGER.info("Response: " + responseBody);
		}
	}
}
