package com.electromagneticsoftware.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electromagneticsoftware.business.entities.Group;
import com.electromagneticsoftware.business.entities.repositories.GroupRepository;

@Service
public class GroupService {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(GroupService.class);

	@Autowired
	private GroupRepository groupRepository;

	public List<Group> findAll(BridgeProperties bridge) {
		List<Group> lights = groupRepository.findAll(bridge);
		return lights;
	}

}
