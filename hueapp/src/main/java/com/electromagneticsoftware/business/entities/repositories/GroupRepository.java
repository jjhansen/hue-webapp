package com.electromagneticsoftware.business.entities.repositories;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.electromagneticsoftware.business.entities.Group;
import com.electromagneticsoftware.business.entities.Groups;
import com.electromagneticsoftware.services.BridgeProperties;

@Repository
public class GroupRepository {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(GroupRepository.class);
	List<Group> groups;
	
	@Autowired
	RestTemplate restTemplate;
	
	public List<Group> findAll(BridgeProperties bridge) {
		String url = "http://" + bridge.getBridgeIp() + "/api/" + bridge.getUsername() + "/groups";
		Groups myGroups = restTemplate.getForObject(url , Groups.class);
		groups = myGroups.getGroups();
		return groups;
	}

	public Group findGroup(Long id, BridgeProperties bridge) {
		String url = "http://" + bridge.getBridgeIp() + "/api/" + bridge.getUsername() + "/groups/" + id;
		Group l = restTemplate.getForObject(url , Group.class);
		return l;
	}
	
}
