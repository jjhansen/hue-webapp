package com.electromagneticsoftware.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electromagneticsoftware.business.entities.Light;
import com.electromagneticsoftware.business.entities.repositories.LightRepository;

@Service
public class LightService {

	@Autowired
	private LightRepository lightRepository;
	
	public Iterable<Light> findAll( BridgeProperties bridge ) {
		return lightRepository.findAll( bridge );
	}
}
