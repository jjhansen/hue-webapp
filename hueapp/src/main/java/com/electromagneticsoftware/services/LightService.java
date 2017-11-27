package com.electromagneticsoftware.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electromagneticsoftware.business.entities.Light;
import com.electromagneticsoftware.business.entities.LightStateUpdate;
import com.electromagneticsoftware.business.entities.repositories.LightRepository;
import com.electromagneticsoftware.controllers.FormLight;
import com.electromagneticsoftware.controllers.LightsForm;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class LightService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LightService.class);

	@Autowired
	private LightRepository lightRepository;
	
	public List<Light> findAll(BridgeProperties bridge) {
		return lightRepository.findAll(bridge);
	}

	public void setXmasLights(BridgeProperties bridge, LightsForm lightsForm) throws HueServiceException {
		List<String> ids = getSelectedLights(lightsForm);
		LightStateUpdate update = new LightStateUpdate();
		update.setOn(true);
		update.setHue(360L);	// red is 360 degrees
		update.setSat(255L);	// max is 255
		update.setBri(255L);	// max is 255
		update.setEffect("none");
		try {
			lightRepository.setState(bridge, ids, update);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new HueServiceException("Unable to set state for lights");
		}
	}

	public void turnOnLights(BridgeProperties bridge, LightsForm lightsForm) throws HueServiceException {
		List<String> ids = getSelectedLights(lightsForm);
		LightStateUpdate update = new LightStateUpdate();
		update.setOn(true);
		try {
			lightRepository.setState(bridge, ids, update);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new HueServiceException("Unable to set state for lights");
		}
	}

	public void turnOffLights(BridgeProperties bridge, LightsForm lightsForm) throws HueServiceException {
		List<String> ids = getSelectedLights(lightsForm);
		LightStateUpdate update = new LightStateUpdate();
		update.setOn(false);
		try {
			lightRepository.setState(bridge, ids, update);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new HueServiceException("Unable to set state for lights");
		}
	}

	public void setLoopLights(BridgeProperties bridge, LightsForm lightsForm) throws HueServiceException {
		List<String> ids = getSelectedLights(lightsForm);
		LightStateUpdate update = new LightStateUpdate();
		update.setOn(true);
		update.setEffect("colorloop");
		try {
			lightRepository.setState(bridge, ids, update);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new HueServiceException("Unable to set state for lights");
		}
	}
	private List<String> getSelectedLights(LightsForm lightsForm) {
		List<String> ids = new ArrayList<String>();
		List<FormLight> lights = lightsForm.getLights();
		for (FormLight light : lights) {
			if (light.getSelected()) {
				ids.add(light.getId());
			}
		}
		return ids;
	}

}
