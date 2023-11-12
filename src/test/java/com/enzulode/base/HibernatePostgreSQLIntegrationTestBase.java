package com.enzulode.base;

import com.enzulode.configuration.DefaultHibernateConfigurerImpl;
import com.enzulode.configuration.HibernateConfigurer;
import com.enzulode.util.FlywayHibernateIntegrator;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
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
	 * This method configures hibernate for integration testing.
	 *
	 */
	@BeforeAll
	public static void configureHibernate()
	{
		ClassicConfiguration flywayConfiguration = new ClassicConfiguration();
		flywayConfiguration.setDriver(CONTAINER.getDriverClassName());
		flywayConfiguration.setUrl(CONTAINER.getJdbcUrl());
		flywayConfiguration.setUser(CONTAINER.getUsername());
		flywayConfiguration.setPassword(CONTAINER.getPassword());

		Map<String, Object> hibernateParameters = new HashMap<>();
		hibernateParameters.put("hibernate.connection.driver_class", CONTAINER.getDriverClassName());
		hibernateParameters.put("hibernate.connection.url", CONTAINER.getJdbcUrl());
		hibernateParameters.put("hibernate.connection.username", CONTAINER.getUsername());
		hibernateParameters.put("hibernate.connection.password", CONTAINER.getPassword());
		hibernateParameters.put("hibernate.show_sql", true);
		hibernateParameters.put("hibernate.format_sql", true);

		HibernateConfigurer configurer = new DefaultHibernateConfigurerImpl();
		sessionFactory = configurer.configure(List.of(new FlywayHibernateIntegrator(flywayConfiguration)), hibernateParameters);
	}
}
