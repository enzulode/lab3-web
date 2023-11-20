package com.enzulode.util;

import com.enzulode.exception.FailedToLoadConfigurationResourceException;

public interface PropertiesLoader<T>
{

	T load(String name) throws FailedToLoadConfigurationResourceException;

}
