package com.enzulode.exception;

public abstract class ConfigurationException extends RuntimeException
{

	public ConfigurationException(String message)
	{
		super(message);
	}

	public ConfigurationException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
