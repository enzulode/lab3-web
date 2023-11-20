package com.enzulode.dao;

import java.io.Serializable;
import java.util.List;

/**
 * An abstract dao contract.
 *
 * @param <T> accessed entity type
 */
public interface Dao<T> extends Serializable
{

	/**
	 * This method fetches all stored entities as list.
	 *
	 * @return a list of stored entities
	 */
	List<T> findAll();

	/**
	 * This method fetches a group of stored entities as list.
	 *
	 * @param start starting entity
	 * @param count fetched group size
	 * @return a list of stored entities
	 */
	List<T> findAll(int start, int count);

	/**
	 * This method adds new entity to a storage.
	 *
	 * @param obj entity to be added
	 * @return entity instance with updated id
	 */
	T add(T obj);

	/**
	 * This method returns an amount of stored entities.
	 *
	 * @return an amount of stored entities
	 */
	int count();

	/**
	 * This method removes all stored entities.
	 *
	 */
	void clear();

}
