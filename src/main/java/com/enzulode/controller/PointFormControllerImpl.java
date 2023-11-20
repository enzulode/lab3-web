package com.enzulode.controller;

import com.enzulode.dao.Dao;
import com.enzulode.model.Point;
import com.enzulode.service.HitCheckingService;
import com.enzulode.ui.model.FormData;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;

/**
 * Point form controller implementation of {@link FormController}.
 *
 */
@Named("formController")
@SessionScoped
@Slf4j
public class PointFormControllerImpl implements FormController
{

	/**
	 * PointDAO instance.
	 *
	 */
	@Inject
	private Dao<Point> pointDAO;

	/**
	 * Hit checking service instance.
	 *
	 */
	@Inject
	private HitCheckingService hitCheckingService;

	/**
	 * This method submits form & executes related business logic.
	 *
	 * @param formData current form data instance
	 */
	public void submitForm(FormData formData)
	{
		Point point = new Point(formData.getX(), formData.getY(), formData.getR());
		point.setResult(
				hitCheckingService.checkHit(point.getPointData())
		);
		pointDAO.add(point);
	}

	/**
	 * This method is responsible for previous results clearing.
	 *
	 */
	public void clearResults()
	{
		pointDAO.clear();
	}

}
