package com.electromagneticsoftware.business.entities.repositories;

import java.util.List;

import com.electromagneticsoftware.business.entities.Bridge;
import com.electromagneticsoftware.business.entities.Light;

public interface LightRepository {

	public List<Light> findAll( Bridge bridge );
	public Light findLight( Long id, Bridge bridge );
}
