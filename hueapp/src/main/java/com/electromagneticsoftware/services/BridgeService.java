package com.electromagneticsoftware.services;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

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

	public BridgeProperties find() throws HueServiceException {
		BridgeProperties bridge = null;
		String ip = properties.getBridgeIp();
		if (StringUtils.isBlank(ip)) {
			bridge = discoverBridge();
			if (null != bridge) {
				ip = bridge.getBridgeIp();
			}
		}
		String username = properties.getUsername();
		if (StringUtils.isNotBlank(ip) && StringUtils.isNotBlank(username)) {
			bridge = login(ip, username, true);
		}
		if (null != bridge) {
			bridge.setSettleTime(properties.getSettleTime());
			bridge.setSleepTime(properties.getSleepTime());
		}
		return bridge;
	}
	
	public BridgeProperties discoverBridge() throws HueServiceException {
//		String ip = "192.168.1.23";
//		BridgeProperties bridge = createUser(ip);
		Set<String> ipAddresses = null;
		try {
			ipAddresses = SsdpClient.discover(5000);
		} catch (IOException e) {
			e.printStackTrace();
			String error = "Unable to discover bridge: " + e.getLocalizedMessage();
			LOGGER.error(error);
			throw new HueServiceException(error);
		}
		BridgeProperties bridge = null;
		for (String ip : ipAddresses) {
			String username = properties.getUsername();
			if (StringUtils.isNotBlank(ip) && StringUtils.isNotBlank(username)) {
				bridge = login(ip, username, false);
				if (null != bridge) {
					saveBridgeProperties(ip, username);
					break;
				}
			}
		}
		return bridge;
	}
	
	public BridgeProperties createUser() throws HueServiceException {
		String ip = properties.getBridgeIp();
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
				String username = usermap.get("username");
				bridge.setUsername(username);
				saveBridgeProperties(ip, username);
			}
			if (response.containsKey("error")) {
				LOGGER.info("Got error from bridge request.");
				@SuppressWarnings("unchecked")
				Map<String, Object> errormap = (Map<String, Object>) response.get("error");
				StringBuilder s = new StringBuilder();
				s.append("Error: ");
				for (String key : errormap.keySet()) {
					LOGGER.info("Error: " + key + " : " + errormap.get(key).toString());
					s.append(key + ": " + errormap.get(key).toString());
					s.append(" ");
				}
				throw new HueServiceException(s.toString());
			}
		}
		return bridge;
	}
	
	private void saveBridgeProperties(String ip, String username) throws HueServiceException {
		properties.setBridgeIp(ip);
		properties.setUsername(username);
		if (null == properties.getSettleTime()) {
			properties.setSettleTime(500L);
		}
		if (null == properties.getSleepTime()) {
			properties.setSleepTime(4500L);
		}
		properties.save();
	}

	private BridgeProperties login(String ip, String username, boolean retry) throws HueServiceException {
		BridgeProperties bridge = new BridgeProperties();
		bridge.setBridgeIp(ip);
		bridge.setUsername(username);
				
		String url = "http://" + bridge.getBridgeIp() + "/api/" + username + "/config";
		Config response = null;
		try {
			response  = restTemplate.getForObject(url, Config.class);
		}
		catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			if (retry) {
				// discover bridge
				bridge = discoverBridge();
				return bridge;
			}
			
		}
		// non-whitelisted users returns a subset of the config
		if (null == response || null == response.getIpaddress()) {
			bridge = null;
		}
		return bridge;
	}

}
