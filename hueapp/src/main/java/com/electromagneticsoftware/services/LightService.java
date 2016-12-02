package com.electromagneticsoftware.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electromagneticsoftware.business.entities.Light;
import com.electromagneticsoftware.business.entities.repositories.LightRepository;

@Service
public class LightService {

	@Autowired
	private LightRepository lightRepository;
	
	public Iterable<Light> findAll() {
		return this.lightRepository.findAll();
	}
}
