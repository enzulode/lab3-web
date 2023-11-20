package com.enzulode.configuration;

import com.enzulode.util.FlywayHibernateIntegrator;
import com.enzulode.util.PropertiesLoader;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.flywaydb.core.api.configuration.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.integrator.spi.Integrator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DBMS configuration bean.
 *
 */
@Named("databaseConfiguration")
@ApplicationScoped
public class DatabaseConfiguration
{

	/**
	 * Properties loader instance.
	 */
	@Inject
	private PropertiesLoader<Configuration> propertiesLoader;

	/**
	 * Hibernate {@link SessionFactory} producer.
	 * Produces {@link SessionFactory} instance in CDI beans context.
	 *
	 * @param configurer hibernate configurer instance
	 * @return hibernate session factory instance
	 */
	@Produces
	public SessionFactory configureSessionFactory(HibernateConfigurer configurer)
	{
		Configuration conf = propertiesLoader.load("database.properties");

		Map<String, Object> params = new HashMap<>();
		params.put("hibernate.connection.driver_class", conf.getDriver());
		params.put("hibernate.connection.url", conf.getUrl());
		params.put("hibernate.connection.username", conf.getUser());
		params.put("hibernate.connection.password", conf.getPassword());
		params.put("hibernate.show_sql", true);
		params.put("hibernate.format_sql", true);

		Integrator flywayIntegrator = new FlywayHibernateIntegrator(conf);
		return configurer.configure(List.of(flywayIntegrator), params);
	}

	/**
	 * Hibernate {@link Session} producer.
	 * Produces {@link Session} in CDI beans context.
	 *
	 * @param sessionFactory hibernate session factory instance
	 * @return hibernate session instance
	 */
	@Produces
	public Session configureSession(SessionFactory sessionFactory)
	{
		return sessionFactory.openSession();
	}

}
