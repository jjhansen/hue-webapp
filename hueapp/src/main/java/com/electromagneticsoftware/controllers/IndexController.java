package com.electromagneticsoftware.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.electromagneticsoftware.business.entities.Light;
import com.electromagneticsoftware.business.entities.repositories.LightRepository;

@Controller
@RequestMapping("/")
public class IndexController {
	
	private final LightRepository lightRepository;
	
	public IndexController( LightRepository lightRepository ) {
		this.lightRepository = lightRepository;
	}
	
	String index() {
		return "index";
	}
	
	@GetMapping
	public ModelAndView list() {
		Iterable<Light> lights = lightRepository.findAll();
		return new ModelAndView( "index", "lights", lights );
	}
	
    @ModelAttribute("allLights")
    public List<Light> populateLights() {
        return lightRepository.findAll();
    }

}
