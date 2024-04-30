package com.enzulode.dao;

import com.enzulode.base.HibernatePostgreSQLIntegrationTestBase;
import com.enzulode.model.Point;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PointDaoTest extends HibernatePostgreSQLIntegrationTestBase
{
	/**
	 * Hibernate session instance.
	 *
	 */
	private Session hibernateSession;

	/**
	 * Point dao instance.
	 *
	 */
	private Dao<Point> pointDao;

	/**
	 * This method configures testing environment before each test.
	 *
	 */
	@BeforeEach
	public void setupHibernateSession()
	{
		hibernateSession = sessionFactory.openSession();
		pointDao = new PointDAOImpl(hibernateSession);
	}

	/**
	 * This method destroys testing environment after each test.
	 *
	 */
	@AfterEach
	public void destroyHibernateSession()
	{
		pointDao = null;
		hibernateSession.close();
	}

	/**
	 * This method tests point creation via DAO.
	 *
	 */
	@Test
	@Order(1)
	@DisplayName("Point creation")
	public void testPointCreation()
	{
		List<Point> points = List.of(
				new Point(1, 2, 3),
				new Point(2, 4 ,2),
				new Point(0, 2, 1)
		);

		for (Point p : points)
			p.setResult(true);

		List<Point> result = new ArrayList<>();
		for (Point p : points)
			result.add(pointDao.add(p));

		for (Point p : result)
		{
			Assertions.assertNotNull(p, "Point was not inserted");
			Assertions.assertNotNull(p.getId(), "Point was not inserted");
		}
	}

	/**
	 * This method tests point counting via DAO.
	 *
	 */
	@Test
	@Order(2)
	@DisplayName("Point count test")
	public void testPointCount()
	{
		Assertions.assertEquals(3, pointDao.count(), "Unexpected amount of points found");
	}

	/**
	 * This method tests point removing from persistence via DAO.
	 *
	 */
	@Test
	@Order(3)
	@DisplayName("Point clear test")
	public void testPointClear()
	{
		pointDao.clear();

		Assertions.assertEquals(0, pointDao.count(), "Unexpected amount of points found");
	}

	/**
	 * This method tests points fetching from persistence via DAO.
	 *
	 */
	@Test
	@Order(4)
	@DisplayName("Point fetching test")
	public void testFindAll()
	{
		List<Point> points = List.of(
				new Point(1, 2, 3),
				new Point(2, 4 ,2.5),
				new Point(0, 2, 3)
		);

		List<Point> inserted = new ArrayList<>();
		for (Point p : points)
			inserted.add(pointDao.add(p));

		List<Point> result = pointDao.findAll();

		result.sort(Comparator.comparingLong(Point::getId));
		inserted.sort(Comparator.comparingLong(Point::getId));

		Assertions.assertEquals(inserted, result, "Unexpected resulting list");
	}
}
