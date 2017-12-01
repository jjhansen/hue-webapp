package com.electromagneticsoftware.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.electromagneticsoftware.business.entities.Light;
import com.electromagneticsoftware.business.entities.LightState;
import com.electromagneticsoftware.business.entities.LightStateUpdate;
import com.electromagneticsoftware.business.entities.repositories.LightRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

public class XmasRunnable implements Runnable {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LightService.class);
	private List<String> ids;
	private LightRepository lightRepository;
	private BridgeProperties bridge;
	private LightState lastState;
	private Integer numChanges = 0;

	@Override
	public void run() {
		LOGGER.info("Starting xmas thread");
		Long settleTime = bridge.getSettleTime();
		Long sleepTime = bridge.getSleepTime();
		Boolean keepGoing = true;
		if (ids.isEmpty()) {
			keepGoing = false;
		}
		else {
			Light light = lightRepository.findLight(Long.valueOf(ids.get(0)), bridge);
			lastState = light.getState();
		}
		while(keepGoing) {
			++numChanges;
			keepGoing = checkForChanges();
			if (keepGoing) {
				LightStateUpdate update = new LightStateUpdate();
				if (numChanges % 2 == 0) {
					setGreen(update);
				}
				else {
					setRed(update);
				}
				try {
					lightRepository.setState(bridge, ids, update);
				} catch (Exception e) {
					LOGGER.error(e.getLocalizedMessage());
					e.printStackTrace();
					keepGoing = false;
				}
				try {
					Thread.sleep(settleTime);
				} catch (InterruptedException e1) {
					LOGGER.info("xmas thread interrupted: " + e1.getLocalizedMessage());
					keepGoing = false;
				}
				try {
					Light light = lightRepository.findLight(Long.valueOf(ids.get(0)), bridge);
					lastState = light.getState();
				} catch (Exception e) {
					LOGGER.info("xmas thread interrupted: " + e.getLocalizedMessage());
					keepGoing = false;
				}
				try {
					Thread.sleep(sleepTime);
				} catch (Exception e) {
					LOGGER.info("xmas thread interrupted: " + e.getLocalizedMessage());
					keepGoing = false;
				}
			}
		}	
		LOGGER.info("Exiting xmas thread");
	}

	private Boolean checkForChanges() {
		Light light = lightRepository.findLight(Long.valueOf(ids.get(0)), bridge);
		LightState currentState = light.getState();
		if (false == currentState.hueSatEquals(lastState)) {
			LOGGER.info("current:  " + currentState.toString());
			LOGGER.info("previous: " + lastState.toString());
		}
		return currentState.equals(lastState);
	}

	private void setRed(LightStateUpdate update) {
		update.setOn(true);
		update.setHue(0L);		// red is 0
		update.setSat(254L);	// max is 254
//		update.setBri(254L);	// max is 254
		update.setEffect("none");
	}

	private void setGreen(LightStateUpdate update) {
		update.setOn(true);
		update.setHue(25500L);	// green is 25500  
		update.setSat(254L);	// max is 254
//		update.setBri(254L);	// max is 254
		update.setEffect("none");
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public LightRepository getLightRepository() {
		return lightRepository;
	}

	public void setLightRepository(LightRepository lightRepository) {
		this.lightRepository = lightRepository;
	}

	public BridgeProperties getBridge() {
		return bridge;
	}

	public void setBridge(BridgeProperties bridge) {
		this.bridge = bridge;
	}

}
