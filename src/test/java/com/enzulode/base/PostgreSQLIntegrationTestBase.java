package com.enzulode.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.PostgreSQLContainer;

/**
 * This class contains basic logic for Postgresql-related testing.
 *
 */
public abstract class PostgreSQLIntegrationTestBase
{

	/**
	 * PostgreSQL container instance.
	 */
	public static PostgreSQLContainer<?> CONTAINER = new PostgreSQLContainer<>("postgres:15.2");


	/**
	 * This method contains all preparation logic for better developer experience
	 * testing postgresql-related functionality.
	 *
	 */
	@BeforeAll
	public static void configureTestcontainers()
	{
		CONTAINER
				.withExposedPorts(5432)
				.withDatabaseName("dev-db")
				.withUsername("dev")
				.withPassword("dev")
				.start();
	}

	/**
	 * This method stops testing container after all test completed.
	 *
	 */
	@AfterAll
	public static void destroy()
	{
		CONTAINER.stop();
	}
}
