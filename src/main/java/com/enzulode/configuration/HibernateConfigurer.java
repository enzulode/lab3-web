package com.enzulode.configuration;

import com.enzulode.exception.HibernateConfigurationFailedException;
import org.hibernate.SessionFactory;
import org.hibernate.integrator.spi.Integrator;

import java.util.List;
import java.util.Map;

/**
 * An abstraction for specific hibernate configurer implementations.
 *
 */
public interface HibernateConfigurer
{

	/**
	 * This method configures hibernate with required integrators and params.
	 *
	 * @return hibernate session factory instance
	 * @throws HibernateConfigurationFailedException if configuration process failed
	 */
	SessionFactory configure(List<Integrator> integrators, Map<String, Object> settings) throws HibernateConfigurationFailedException;
}
