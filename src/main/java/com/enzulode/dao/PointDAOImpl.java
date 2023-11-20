package com.enzulode.dao;

import com.enzulode.model.Point;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

/**
 * {@link Dao} implementation for {@link Point} entity.
 *
 */
@Named("pointDAO")
@SessionScoped
@NoArgsConstructor
@AllArgsConstructor
public class PointDAOImpl implements Dao<Point>
{

	/**
	 * Hibernate session instance.
	 *
	 */
	@Inject
	private Session hibernateSession;

	/**
	 * This method fetches all stored entities as list.
	 *
	 * @return a list of stored entities
	 */
	public List<Point> findAll()
	{
		return hibernateSession.createQuery("SELECT point FROM Point point", Point.class)
				.getResultList();
	}

	/**
	 * This method fetches a group of stored entities as list.
	 *
	 * @param start starting entity
	 * @param count fetched group size
	 * @return a list of stored entities
	 */
	public List<Point> findAll(int start, int count)
	{
		return hibernateSession.createQuery("SELECT point FROM Point point", Point.class)
				.setFirstResult(start)
				.setMaxResults(count)
				.getResultList();
	}

	/**
	 * This method adds new entity to a storage.
	 *
	 * @param point entity to be added
	 * @return entity instance with updated id
	 */
	public Point add(Point point)
	{
		var transaction = hibernateSession.beginTransaction();
		hibernateSession.merge(point);
		transaction.commit();
		return point;
	}

	/**
	 * This method returns an amount of stored entities.
	 *
	 * @return an amount of stored entities
	 */
	public int count()
	{
		return hibernateSession.createQuery("SELECT count(*) FROM Point point", Number.class)
				.getSingleResult()
				.intValue();
	}

	/**
	 * This method removes all stored entities.
	 *
	 */
	public void clear()
	{
		var transaction = hibernateSession.beginTransaction();
		hibernateSession.createMutationQuery("DELETE FROM Point")
				.executeUpdate();
		transaction.commit();
	}

}
