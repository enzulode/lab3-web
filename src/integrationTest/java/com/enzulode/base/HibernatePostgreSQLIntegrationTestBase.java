package com.enzulode.base;

import com.enzulode.configuration.DefaultHibernateConfigurerImpl;
import com.enzulode.configuration.HibernateConfigurer;
import com.enzulode.util.FlywayHibernateIntegrator;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.flywaydb.core.api.configuration.Configuration;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class contains basic logic for hibernate-related testing.
 *
 */
public abstract class HibernatePostgreSQLIntegrationTestBase extends PostgreSQLIntegrationTestBase
{

	/**
	 * Hibernate session factory instance.
	 *
	 */
	public static SessionFactory sessionFactory;

	/**
	 * Hibernate parameters holder.
	 *
	 */
	private static final Map<String, Object> hibernateParams = new HashMap<>();

	/**
	 * This method configures hibernate parameters using the superclass database container properties.
	 *
	 */
	private static void configureHibernateParams() {
		hibernateParams.clear();
		hibernateParams.put("hibernate.connection.driver_class", CONTAINER.getDriverClassName());
		hibernateParams.put("hibernate.connection.url", CONTAINER.getJdbcUrl());
		hibernateParams.put("hibernate.connection.username", CONTAINER.getUsername());
		hibernateParams.put("hibernate.connection.password", CONTAINER.getPassword());
		hibernateParams.put("hibernate.show_sql", true);
		hibernateParams.put("hibernate.format_sql", true);
	}

	private static Configuration configureFlywayParams() {
		ClassicConfiguration flywayConfiguration = new ClassicConfiguration();
		flywayConfiguration.setDriver(CONTAINER.getDriverClassName());
		flywayConfiguration.setUrl(CONTAINER.getJdbcUrl());
		flywayConfiguration.setUser(CONTAINER.getUsername());
		flywayConfiguration.setPassword(CONTAINER.getPassword());
		return flywayConfiguration;
	}

	/**
	 * This method configures hibernate for integration testing.
	 *
	 */
	@BeforeAll
	public static void configureHibernate()
	{
		// perform flyway params configuration using the specific params configuration method
		Configuration flywayConfiguration = configureFlywayParams();

		// perform hibernate params configuration using the specific params configuration method
		configureHibernateParams();

		HibernateConfigurer configurer = new DefaultHibernateConfigurerImpl();
		sessionFactory = configurer.configure(List.of(new FlywayHibernateIntegrator(flywayConfiguration)), hibernateParams);
	}
}
