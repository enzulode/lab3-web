package com.enzulode.configuration;

import com.enzulode.exception.HibernateConfigurationFailedException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.integrator.spi.Integrator;

import java.util.List;
import java.util.Map;

/**
 * Default hibernate configurer implementation.
 *
 */
public class DefaultHibernateConfigurerImpl implements HibernateConfigurer
{
	/**
	 * Hibernate bootstrap service registry builder instance.
	 *
	 */
	private final BootstrapServiceRegistryBuilder bootstrapServiceRegistryBuilder;

	/**
	 * Default hibernate configurer implementation constructor.
	 *
	 */
	public DefaultHibernateConfigurerImpl()
	{
		bootstrapServiceRegistryBuilder = new BootstrapServiceRegistryBuilder();
	}

	/**
	 * This method configures hibernate with required integrators and params.
	 *
	 * @return hibernate session factory instance
	 * @throws HibernateConfigurationFailedException if configuration process failed
	 */
	@Override
	public SessionFactory configure(List<Integrator> integrators, Map<String, Object> settings) throws HibernateConfigurationFailedException
	{
		if (integrators == null)
			throw new HibernateConfigurationFailedException("Hibernate integrators list cannot be null");

		if (settings == null)
			throw new HibernateConfigurationFailedException("Hibernate settings map cannot be null");

		for (Integrator integrator : integrators)
			if (integrator != null)
				bootstrapServiceRegistryBuilder.applyIntegrator(integrator);

		try(
				final BootstrapServiceRegistry bootstrapServiceRegistry = bootstrapServiceRegistryBuilder.build();
				final StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder(bootstrapServiceRegistry)
						.configure()
						.applySettings(settings)
						.build()
		)
		{
			return new MetadataSources(standardServiceRegistry)
					.buildMetadata()
					.buildSessionFactory();
		}
		catch (Exception e)
		{
			throw new HibernateConfigurationFailedException("Hibernate configuration failed", e);
		}
	}
}
