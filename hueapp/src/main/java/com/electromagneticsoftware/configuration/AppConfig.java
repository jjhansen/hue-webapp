package com.electromagneticsoftware.configuration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.DefaultPropertiesPersister;

import com.electromagneticsoftware.services.HueServiceException;

@Configuration
@PropertySource(value="file:hue-app.properties", ignoreResourceNotFound=true)
public class AppConfig {

	public void save(String bridgeIp, String username) throws HueServiceException {
		try {
			Properties props = new Properties();
			props.setProperty("com.electromagneticsoftware.bridgeIp", bridgeIp);
			props.setProperty("com.electromagneticsoftware.username", username);
			File f = new File("hue-app.properties");
			OutputStream out = new FileOutputStream(f);
			DefaultPropertiesPersister p = new DefaultPropertiesPersister();
			p.store(props, out, "HueApp properites");
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new HueServiceException("Unable to save properties: " + e.getMessage());
		}
	}

	
}
