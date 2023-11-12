package com.enzulode.exception;

public class FlywayConfigurationFailedException extends ConfigurationException
{
	public FlywayConfigurationFailedException(String message)
	{
		super(message);
	}

	public FlywayConfigurationFailedException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
