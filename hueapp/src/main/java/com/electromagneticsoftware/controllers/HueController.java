package com.electromagneticsoftware.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.electromagneticsoftware.business.entities.Light;
import com.electromagneticsoftware.services.BridgeProperties;
import com.electromagneticsoftware.services.BridgeService;
import com.electromagneticsoftware.services.LightService;

@Controller
public class HueController {
	
	@Autowired
	private LightService lightService;
	@Autowired
	private BridgeService bridgeService;
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public String list(Model model) {
		BridgeProperties bridge = bridgeService.find();
		if (null == bridge) {
			return "discoverBridge";
		}
		return showBridgeAndLights(model, bridge);
	}	
	
	private String showBridgeAndLights(Model model, BridgeProperties bridge) {
		model.addAttribute("bridge", bridge);
		Iterable<Light> lights = lightService.findAll( bridge );
		model.addAttribute("allLights", lights);
		return "index";
	}

	@RequestMapping(value = "/createUser", method=RequestMethod.GET)
	public String createUser(Model model) {
		BridgeProperties bridge = bridgeService.discoverBridge();
		if (null == bridge) {
			model.addAttribute("errMsg", "Error creating user");
			return "discoverBridge";
		}
		return showBridgeAndLights(model, bridge);
	}

}
