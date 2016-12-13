package com.electromagneticsoftware.business.entities.repositories;

import org.springframework.stereotype.Repository;

import com.electromagneticsoftware.business.entities.Bridge;

@Repository
public class BridgeRepository {
	private Bridge bridge;

	public Bridge find() {
		// TODO Load bridge information from property file
		if ( null == bridge ) {
			bridge = new Bridge();
			bridge.setIpAddress( "192.168.2.111" );
			bridge.setIpAddress( "10.0.0.16" );
//			bridge.setUsername( "3f451a9f534fab0714d7b6bc27208ba3" );
			bridge.setUsername( "6sLwnkZeilDrDIm2M62DvuKcWX9KNSZ8tN4eouey" );
			
		}
		return bridge;
	}
	
	public void update( Bridge bridge ) {
		this.bridge = bridge;
	}
	
}
