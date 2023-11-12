package com.enzulode.configuration;

import com.enzulode.exception.FlywayConfigurationFailedException;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.Configuration;

/**
 * An abstraction for specific flyway configurer implementations.
 *
 */
public interface FlywayConfigurer
{

	/**
	 * This method configures flyway with required params.
	 *
	 * @return flyway instance
	 * @throws FlywayConfigurationFailedException if configuration process failed
	 */
	Flyway configure(Configuration configuration) throws FlywayConfigurationFailedException;
}
