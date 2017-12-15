package com.electromagneticsoftware.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electromagneticsoftware.business.entities.Group;
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
	
	public List<Light> findAll(BridgeProperties bridge, List<Group> groups) {
		List<Light> lights = lightRepository.findAll(bridge);
		Map<String, List<String>> lightRooms = new HashMap<String, List<String>>();
		for (Group group : groups) {
			String name = group.getName();
			String[] groupLights = group.getLights();
			for (String id : groupLights) {
				List<String> rooms = lightRooms.get(id);
				if (null == rooms) {
					rooms = new ArrayList<String>();
					lightRooms.put(id, rooms);
				}
				rooms.add(name);
			}
		}
		for (Light light : lights) {
			List<String> rooms = lightRooms.get(light.getId());
			light.setRooms(rooms);
		}
		return lights;
	}

	public void setXmasLights(BridgeProperties bridge, LightsForm lightsForm) throws HueServiceException {
		List<String> ids = getSelectedLights(lightsForm);
		XmasRunnable myRunnable = new XmasRunnable();
		myRunnable.setIds(ids);
		myRunnable.setBridge(bridge);
		myRunnable.setLightRepository(lightRepository);
		String name = "xmas " + ids.toString();
		Thread thread = new Thread(myRunnable, name);
		thread.start();		
	}

	public void turnOnLights(BridgeProperties bridge, LightsForm lightsForm) throws HueServiceException {
		List<String> ids = getSelectedLights(lightsForm);
		LightStateUpdate update = new LightStateUpdate();
		update.setOn(true);
		try {
			lightRepository.setState(bridge, ids, update);
		} catch (JsonProcessingException e) {
			LOGGER.error("Unable to set state for lights");
			LOGGER.error(e.getLocalizedMessage());
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
			LOGGER.error("Unable to set state for lights");
			LOGGER.error(e.getLocalizedMessage());
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
			LOGGER.error("Unable to set state for lights");
			LOGGER.error(e.getLocalizedMessage());
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

	public void setSoundersLights(BridgeProperties bridge, LightsForm lightsForm) {
		List<String> ids = getSelectedLights(lightsForm);
		SoundersRunnable myRunnable = new SoundersRunnable();
		myRunnable.setIds(ids);
		myRunnable.setBridge(bridge);
		myRunnable.setLightRepository(lightRepository);
		String name = "sounders " + ids.toString();
		Thread thread = new Thread(myRunnable, name);
		thread.start();		
	}

}
