package com.enzulode.util;

import com.enzulode.exception.FailedToLoadConfigurationResourceException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.flywaydb.core.api.configuration.Configuration;

import java.io.IOException;
import java.util.Properties;

@Named("databaseConfigurationLoader")
@ApplicationScoped
@Slf4j
public class FlywayConfigurationResourcesPropertiesLoader implements PropertiesLoader<Configuration>
{

	@Override
	public Configuration load(String name) throws FailedToLoadConfigurationResourceException
	{
		try
		{
			log.info("Fetching database configuration from resources");
			Properties properties = new Properties();
			properties.load(getClass().getClassLoader().getResourceAsStream(name));

			ClassicConfiguration flywayConfiguration = new ClassicConfiguration();
			flywayConfiguration.setDriver(properties.getProperty("db.driver"));
			flywayConfiguration.setUrl(properties.getProperty("db.url"));
			flywayConfiguration.setUser(properties.getProperty("db.user"));
			flywayConfiguration.setPassword(properties.getProperty("db.password"));
			return flywayConfiguration;
		}
		catch (IOException e)
		{
			log.error("Database configuration fetching failed", e);
			throw new FailedToLoadConfigurationResourceException("Unable to find configuration resource", e);
		}
	}
}
