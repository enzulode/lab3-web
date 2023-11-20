package com.enzulode.exception;

public class FailedToLoadConfigurationResourceException extends ConfigurationException
{
	public FailedToLoadConfigurationResourceException(String message)
	{
		super(message);
	}

	public FailedToLoadConfigurationResourceException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
