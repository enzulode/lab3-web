package com.enzulode.configuration;

import com.enzulode.exception.FlywayConfigurationFailedException;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.Configuration;

/**
 * Default flyway configurer implementation.
 *
 */
@Slf4j
public class DefaultFlywayConfigurerImpl implements FlywayConfigurer
{

	/**
	 * This method configures flyway with required params.
	 *
	 * @return flyway instance
	 * @throws FlywayConfigurationFailedException if configuration process failed
	 */
	@Override
	public Flyway configure(Configuration configuration) throws FlywayConfigurationFailedException
	{
		log.debug("Performing flyway configuration");
		if (configuration == null)
			throw new FlywayConfigurationFailedException("Flyway configuration is not present");

		return new Flyway(configuration);
	}
}
