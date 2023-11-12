package com.enzulode.util;

import com.enzulode.configuration.DefaultFlywayConfigurerImpl;
import com.enzulode.configuration.FlywayConfigurer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.Configuration;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.spi.BootstrapContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

/**
 * Hibernate Flyway integrator implementation.
 *
 */
@RequiredArgsConstructor
@Slf4j
public class FlywayHibernateIntegrator implements Integrator
{

	/**
	 * Flyway configuration instance.
	 *
	 */
	private final Configuration flywayConfiguration;

	/**
	 * Perform integration.
	 *
	 * @param metadata The fully initialized boot-time mapping model
	 * @param bootstrapContext The context for bootstrapping of the SessionFactory
	 * @param sessionFactory The SessionFactory being created
	 */
	@Override
	public void integrate(@NonNull Metadata metadata, @NonNull BootstrapContext bootstrapContext, @NonNull SessionFactoryImplementor sessionFactory)
	{
		FlywayConfigurer configurer = new DefaultFlywayConfigurerImpl();
		Flyway flyway = configurer.configure(flywayConfiguration);

		log.debug("Performing flyway migrations");
		flyway.migrate();
	}

	/**
	 * Integration shutdown callback.
	 *
	 * @param sessionFactory The session factory being closed.
	 * @param serviceRegistry That session factory's service registry
	 */
	@Override
	public void disintegrate(@NonNull SessionFactoryImplementor sessionFactory, @NonNull SessionFactoryServiceRegistry serviceRegistry)
	{
//		nothing :c
	}
}
