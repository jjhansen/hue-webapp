package com.electromagneticsoftware.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.electromagneticsoftware.business.entities.LightStateUpdate;
import com.fasterxml.jackson.core.JsonProcessingException;

public class XmasRunnable extends RunnableBase {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(XmasRunnable.class);
	private static final String THREAD_NAME = "xmas";

	@Override
	protected Boolean updateLights() {
		Boolean keepGoing = true;
		LightStateUpdate update = new LightStateUpdate();
		if (numChanges % 2 == 0) {
			setGreen(update);
		}
		else {
			setRed(update);
		}
		try {
			lightRepository.setState(bridge, ids, update);
		} catch (JsonProcessingException e) {
			LOGGER.error(e.getLocalizedMessage());
			e.printStackTrace();
			keepGoing = false;
		}
		return keepGoing;
	}

	@Override
	protected String getThreadName() {
		return THREAD_NAME;
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
}
