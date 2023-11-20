package com.enzulode.ui.model;

import com.enzulode.dao.Dao;
import com.enzulode.model.Point;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import java.util.List;
import java.util.Map;

/**
 * This class is an implementation of {@link LazyDataModel} for primefaces data table.
 *
 */
@Named("pointDataModel")
@SessionScoped
public class PointTableDataModel extends LazyDataModel<Point>
{

	/**
	 * Point data.
	 *
	 */
	@Inject
	private Dao<Point> pointDAO;

	/**
	 * This method counts stored entities.
	 *
	 * @param filterBy a map with all filter information (only relevant for DataTable, not for example DataView)
	 * @return an amount of stored entities
	 */
	@Override
	public int count(Map<String, FilterMeta> filterBy)
	{
		return pointDAO.count();
	}

	/**
	 * This method fetches pages for data table.
	 *
	 * @param first the first entry
	 * @param pageSize the page size
	 * @param sortBy a map with all sort information (only relevant for DataTable, not for eg DataView)
	 * @param filterBy a map with all filter information (only relevant for DataTable, not for eg DataView)
	 * @return a group of points in a list
	 */
	@Override
	public List<Point> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy)
	{
		return pointDAO.findAll(first, pageSize);
	}
}
