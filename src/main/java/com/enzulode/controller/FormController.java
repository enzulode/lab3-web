package com.enzulode.controller;

import com.enzulode.ui.model.FormData;

import java.io.Serializable;

/**
 * An abstraction for application form controller.
 *
 */
public interface FormController extends Serializable
{

	/**
	 * This method submits form & executes related business logic.
	 *
	 * @param formData current form data instance
	 */
	void submitForm(FormData formData);

	/**
	 * This method is responsible for previous results clearing.
	 *
	 */
	void clearResults();
}
