package com.enzulode.exception;

public class HibernateConfigurationFailedException extends ConfigurationException
{
	public HibernateConfigurationFailedException(String message)
	{
		super(message);
	}

	public HibernateConfigurationFailedException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
