package com.electromagneticsoftware.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electromagneticsoftware.business.entities.Bridge;
import com.electromagneticsoftware.business.entities.repositories.BridgeRepository;

@Service
public class BridgeService {
	@Autowired
	private BridgeRepository bridgeRepository;
	
	public Bridge find() {
		return this.bridgeRepository.find();
	}

}
