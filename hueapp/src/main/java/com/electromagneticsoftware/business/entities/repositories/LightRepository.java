package com.electromagneticsoftware.business.entities.repositories;

import java.util.List;

import com.electromagneticsoftware.business.entities.Light;

public interface LightRepository {

	public List<Light> findAll();
	public Light findLight( Long id );
	public String getIp();
	public void setIp( String ip );
	public String getUsername();
	public void setUsername( String username );
}
