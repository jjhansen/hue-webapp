package com.electromagneticsoftware.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.electromagneticsoftware.business.entities.LightStateUpdate;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Xmas2Runnable extends RunnableBase {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(XmasRunnable.class);
	private static final String THREAD_NAME = "xmas2";
	private List<String> idsColor1 = new ArrayList<String>();
	private List<String> idsColor2 = new ArrayList<String>();

	@Override
	protected Boolean updateLights() {
		Boolean keepGoing = true;
		LightStateUpdate updateColor1 = new LightStateUpdate();
		LightStateUpdate updateColor2 = new LightStateUpdate();
		if (numChanges % 2 == 0) {
			setGreen(updateColor1);
			setRed(updateColor2);
		}
		else {
			setGreen(updateColor2);
			setRed(updateColor1);
		}
		try {
			lightRepository.setState(bridge, idsColor1, updateColor1);
			lightRepository.setState(bridge, idsColor2, updateColor2);
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
	
	public void setIds(List<String> ids) {
		super.setIds(ids);
		Integer numLights = 0;
		for (String id : ids) {
			if (numLights % 2 == 0) {
				idsColor1.add(id);
			}
			else {
				idsColor2.add(id);
			}
			++numLights;
		}
	}


}
