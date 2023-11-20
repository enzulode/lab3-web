package com.enzulode.controller;

import java.io.Serializable;

/**
 * An abstraction for graph controller.
 *
 */
public interface GraphController extends Serializable
{

	/**
	 * This method fetches all existing points and returns them as json using GSON.
	 *
	 * @return json string that represents an array of existing points
	 */
	String fetchPoints();

	/**
	 * This method handles graph click.
	 *
	 */
	void handleGraphClick();
}
