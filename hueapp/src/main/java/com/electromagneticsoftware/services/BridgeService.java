package com.electromagneticsoftware.services;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.electromagneticsoftware.business.entities.ApplicationLogin;
import com.electromagneticsoftware.business.entities.Config;

@Service
public class BridgeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BridgeService.class);

	@Autowired
	RestTemplate restTemplate;
		
	private final BridgeProperties properties;
	
	@Autowired
	public BridgeService(BridgeProperties properties) {
		this.properties = properties;
	}


	public BridgeProperties find() {
		BridgeProperties bridge = null;
		String ip = properties.getBridgeIp();
		String username = properties.getUsername();
		if (StringUtils.isNotBlank(ip) && StringUtils.isNotBlank(username)) {
			bridge = login(ip, username);
		}
		return bridge;
	}
	
	public BridgeProperties discoverBridge() {
		String ip = "192.168.1.23";
		// TODO: use SSDP to find the bridge
		BridgeProperties bridge = createUser(ip);
		if (null != bridge) {
			String username = bridge.getUsername();
			properties.setBridgeIp(ip);
			properties.setUsername(username);
			properties.save();
		}
		return bridge;
	}
	
	private BridgeProperties createUser(String ip) {
		BridgeProperties bridge = null;
		ApplicationLogin login = new ApplicationLogin();
		String url = "http://" + ip + "/api";
		Object[] loginResponse = restTemplate.postForObject(url, login, Object[].class);
		for (Object obj : loginResponse) {
			LOGGER.info(obj.toString());
			@SuppressWarnings("unchecked")
			Map<String, Object> response = (Map<String, Object>) obj;
			if (response.containsKey("success")) {
				@SuppressWarnings("unchecked")
				Map<String, String> usermap = (Map<String, String>) response.get("success");
				bridge = new BridgeProperties();
				bridge.setBridgeIp(ip);
				bridge.setUsername(usermap.get("username"));
			}
			if (response.containsKey("error")) {
				LOGGER.info("Got error from bridge request.");
				@SuppressWarnings("unchecked")
				Map<String, Object> errormap = (Map<String, Object>) response.get("error");
				for (String key : errormap.keySet()) {
					LOGGER.info("Error: " + key + " : " + errormap.get(key).toString());
				}
			}
		}
		return bridge;
	}
	
	private BridgeProperties login(String ip, String username) {
		BridgeProperties bridge = new BridgeProperties();
		bridge.setBridgeIp(ip);
		bridge.setUsername(username);
				
		String url = "http://" + bridge.getBridgeIp() + "/api/" + username + "/config";
		Config response  = restTemplate.getForObject(url, Config.class);
		// non-whitelisted users returns a subset of the config
		if (null == response.getIpaddress()) {
			bridge = null;
		}
		return bridge;
	}

}
