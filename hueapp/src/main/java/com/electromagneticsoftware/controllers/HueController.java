package com.electromagneticsoftware.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.electromagneticsoftware.business.entities.Light;
import com.electromagneticsoftware.services.BridgeProperties;
import com.electromagneticsoftware.services.BridgeService;
import com.electromagneticsoftware.services.HueServiceException;
import com.electromagneticsoftware.services.LightService;

@Controller
public class HueController {
	
	@Autowired
	private LightService lightService;
	@Autowired
	private BridgeService bridgeService;

	private BridgeProperties bridge;
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public String list(Model model) {
		if (null == bridge) {
			bridge = bridgeService.find();
			if (null == bridge) {
				return "discoverBridge";
			}
		}	
		showBridgeAndLights(model, bridge);
		return "index";
	}	
		
	private void showBridgeAndLights(Model model, BridgeProperties bridge) {
		model.addAttribute("bridge", bridge);
		List<Light> lights = lightService.findAll(bridge);
		lights.sort((m1, m2) -> {
			return (Integer.valueOf(m1.getId()).compareTo(Integer.valueOf(m2.getId())));
		});
		LightsForm form = new LightsForm(lights);
		model.addAttribute("lightsForm", form);
	}

	@RequestMapping(value = "/createUser", method=RequestMethod.GET)
	public String createUser(Model model) {
		BridgeProperties bridge;
		try {
			bridge = bridgeService.discoverBridge();
		} catch (HueServiceException e) {
			model.addAttribute("errorMessage", "Error authorizing application");
			model.addAttribute("errorDetail", e.getMessage());
			return "discoverBridge";
		}
		showBridgeAndLights(model, bridge);
		return "redirect:/";
	}

	@RequestMapping(value = "/manageLights", method=RequestMethod.POST, params={"xmas", "!on", "!off", "!loop"})
	public String xmasLights(LightsForm lightsForm, Model model) {
		if (null == bridge) {
			bridge = bridgeService.find();
			if (null == bridge) {
				return "discoverBridge";
			}
		}	
		try {
			lightService.setXmasLights(bridge, lightsForm);
		} catch (HueServiceException e) {
			model.addAttribute("errorDetail", e.getMessage());
			return "redirect:/";
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/manageLights", method=RequestMethod.POST, params={"!xmas", "on", "!off", "!loop"})
	public String onLights(LightsForm lightsForm, Model model) {
		if (null == bridge) {
			bridge = bridgeService.find();
			if (null == bridge) {
				return "discoverBridge";
			}
		}	
		try {
			lightService.turnOnLights(bridge, lightsForm);
		} catch (HueServiceException e) {
			model.addAttribute("errorDetail", e.getMessage());
			return "redirect:/";
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/manageLights", method=RequestMethod.POST, params={"!xmas", "!on", "off", "!loop"})
	public String offLights(LightsForm lightsForm, Model model) {
		if (null == bridge) {
			bridge = bridgeService.find();
			if (null == bridge) {
				return "discoverBridge";
			}
		}	
		try {
			lightService.turnOffLights(bridge, lightsForm);
		} catch (HueServiceException e) {
			model.addAttribute("errorDetail", e.getMessage());
			return "redirect:/";
		}
		return "redirect:/";
	}
	@RequestMapping(value = "/manageLights", method=RequestMethod.POST, params={"!xmas", "!on", "!off", "loop"})
	public String loopLights(LightsForm lightsForm, Model model) {
		if (null == bridge) {
			bridge = bridgeService.find();
			if (null == bridge) {
				return "discoverBridge";
			}
		}	
		try {
			lightService.setLoopLights(bridge, lightsForm);
		} catch (HueServiceException e) {
			model.addAttribute("errorDetail", e.getMessage());
			return "redirect:/";
		}
		return "redirect:/";
	}

}
