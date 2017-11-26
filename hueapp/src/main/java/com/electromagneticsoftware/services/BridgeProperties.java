package com.electromagneticsoftware.services;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.electromagneticsoftware.configuration.AppConfig;

@Component
@ConfigurationProperties(prefix="com.electromagneticsoftware")
public class BridgeProperties implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private AppConfig appConfig;
	
	private String bridgeIp;
	private String username;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bridgeIp == null) ? 0 : bridgeIp.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BridgeProperties other = (BridgeProperties) obj;
		if (bridgeIp == null) {
			if (other.bridgeIp != null)
				return false;
		} else if (!bridgeIp.equals(other.bridgeIp))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	public String getBridgeIp() {
		return bridgeIp;
	}
	
	public void setBridgeIp(String bridgeIp) {
		this.bridgeIp = bridgeIp;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void save() throws HueServiceException {
		appConfig.save(bridgeIp, username);
	}
}
