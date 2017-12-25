package com.electromagneticsoftware.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.electromagneticsoftware.business.entities.Light;
import com.electromagneticsoftware.business.entities.LightState;
import com.electromagneticsoftware.business.entities.repositories.LightRepository;

public abstract class RunnableBase implements Runnable {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RunnableBase.class);
	protected List<String> ids;
	protected LightRepository lightRepository;
	protected BridgeProperties bridge;
	protected LightState lastState;
	protected Integer numChanges = 0;
	protected abstract Boolean updateLights();
	protected abstract String getThreadName();
	
	@Override
	public void run() {
		LOGGER.info("Starting " + getThreadName() + " thread");
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
				keepGoing = updateLights();
				if (false == keepGoing) {
					break;
				}
				try {
					Thread.sleep(settleTime);
				} catch (InterruptedException e) {
					LOGGER.info(getThreadName() + " thread interrupted: " + e.getLocalizedMessage());
					keepGoing = false;
					break;
				}
				Light light = lightRepository.findLight(Long.valueOf(ids.get(0)), bridge);
				lastState = light.getState();
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					LOGGER.info(getThreadName() + " thread interrupted: " + e.getLocalizedMessage());
					keepGoing = false;
					break;
				}
			}
		}	
		LOGGER.info("Exiting " + getThreadName() + " thread");
	}

	private Boolean checkForChanges() {
		Light light = lightRepository.findLight(Long.valueOf(ids.get(0)), bridge);
		LightState currentState = light.getState();
		boolean result = currentState.hueSatEquals(lastState);
		if (!result) {
			LOGGER.info("current:  " + currentState.toString());
			LOGGER.info("previous: " + lastState.toString());
		}
		return result;
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
