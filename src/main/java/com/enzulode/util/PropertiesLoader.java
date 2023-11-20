package com.enzulode.util;

import com.enzulode.exception.FailedToLoadConfigurationResourceException;

/**
 * General abstraction for properties loaders.
 *
 * @param <T> properties instance expected to be produced
 */
public interface PropertiesLoader<T>
{

	/**
	 * This method performs properties loading.
	 *
	 * @param name properties file name
	 * @return a generic configuration instance
	 * @throws FailedToLoadConfigurationResourceException if properties file was not found
	 */
	T load(String name) throws FailedToLoadConfigurationResourceException;

}
