package com.electromagneticsoftware.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.electromagneticsoftware.business.entities.Bridge;
import com.electromagneticsoftware.business.entities.Light;
import com.electromagneticsoftware.business.entities.repositories.BridgeRepository;
import com.electromagneticsoftware.business.entities.repositories.LightRepository;

@Controller
@RequestMapping("/")
public class IndexController {
	
	private final LightRepository lightRepository;
	private final BridgeRepository bridgeRepository;
	
	public IndexController( LightRepository lightRepository, BridgeRepository bridgeRepository ) {
		this.lightRepository = lightRepository;
		this.bridgeRepository = bridgeRepository;
	}
	
	String index() {
		return "index";
	}
	
	@GetMapping
	public ModelAndView list() {
		Bridge bridge = bridgeRepository.find();
		Iterable<Light> lights = lightRepository.findAll( bridge );
		return new ModelAndView( "index", "lights", lights );
	}
	
    @ModelAttribute("allLights")
    public List<Light> populateLights() {
        return lightRepository.findAll( bridgeRepository.find() );
    }
    
    @ModelAttribute("bridge")
    public Bridge populateBridge() {
    	return bridgeRepository.find();
    }
}
