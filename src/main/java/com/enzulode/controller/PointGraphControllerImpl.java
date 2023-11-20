package com.enzulode.controller;

import com.enzulode.dao.Dao;
import com.enzulode.model.Point;
import com.enzulode.service.HitCheckingService;
import com.google.gson.Gson;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * Point graph implementation of {@link GraphController}.
 *
 */
@Named("graphController")
@SessionScoped
@Slf4j
public class PointGraphControllerImpl implements GraphController
{

	/**
	 * PointDAO instance.
	 *
	 */
	@Inject
	private Dao<Point> pointDAO;

	/**
	 * HitCheckingService instance.
	 *
	 */
	@Inject
	private HitCheckingService hitCheckingService;

	/**
	 * This method fetches all existing points and returns them as json using GSON.
	 *
	 * @return json string that represents an array of existing points
	 */
	public String fetchPoints()
	{
		List<Point> points = pointDAO.findAll();
		return (new Gson()).toJson(points);
	}

	/**
	 * This method handles graph click.
	 *
	 */
	public void handleGraphClick()
	{
		final Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRequestParameterMap();

		try
		{
			int x = Integer.parseInt(params.get("x"));
			double y = Double.parseDouble(params.get("y"));
			double r = Double.parseDouble(params.get("r"));

			Point point = new Point(x, y, r);
			point.setResult(
					hitCheckingService.checkHit(point.getPointData())
			);
			pointDAO.add(point);
		}
		catch (NumberFormatException e)
		{
			log.error("Failed to handle graph click", e);
		}
	}

}
