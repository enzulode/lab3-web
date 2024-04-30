package com.enzulode.dao;

import com.enzulode.base.HibernatePostgreSQLIntegrationTestBase;
import com.enzulode.model.Point;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
		pointDao.clear();
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
	@Transactional
	@DisplayName("Point creation")
	public void trySaveSomePoints_then_savingPerformedCorrectly()
	{
		// testing data to be saved
		List<Point> points = List.of(
				new Point(1, 2, 3),
				new Point(2, 4 ,2),
				new Point(0, 2, 1),
				new Point(2, 2, 2),
				new Point(2, 4 ,3),
				new Point(1, 2, 1)
		);

		// assume, that point hits the desired area
		points.forEach(p -> p.setResult(true));

		// point saving result
		List<Point> savingResult = points.stream()
		                                 .map(pointDao::add)
		                                 .toList();

		Assertions.assertEquals(points.size(), pointDao.count(), "Expected another amount of points stored in the database: means that database was not clear or not every point was inserted");
		savingResult.forEach(p -> {
			Assertions.assertNotNull(p, "Point is null: means that point was not inserted.");
			Assertions.assertNotNull(p.getId(), "Point id is null: means that point was not inserted.");
		});
	}

	/**
	 * This method tests point removing from persistence via DAO.
	 *
	 */
	@Test
	@Transactional
	@DisplayName("Point clear test")
	public void tryClearDatabase_then_storedPointsAmountIsZero()
	{
		// testing data to be saved
		List<Point> points = List.of(
				new Point(1, 2, 3),
				new Point(2, 4 ,2),
				new Point(0, 2, 1),
				new Point(2, 2, 2),
				new Point(2, 4 ,3),
				new Point(1, 2, 1)
		);

		// assume, that point hits the desired area
		points.forEach(p -> p.setResult(true));

		// point saving result
		List<Point> savingResult = points.stream()
		                                 .map(pointDao::add)
		                                 .toList();

		Assertions.assertEquals(points.size(), pointDao.count(), "Expected another amount of points stored in the database: means that database was not clear or not every point was inserted");
		savingResult.forEach(p -> {
			Assertions.assertNotNull(p, "Point is null: means that point was not inserted.");
			Assertions.assertNotNull(p.getId(), "Point id is null: means that point was not inserted.");
		});

		pointDao.clear();
		Assertions.assertEquals(0, pointDao.count(), "Database is not empty: means that clear() method is not working");
	}

	/**
	 * This method tests points fetching from persistence via DAO.
	 *
	 */
	@Test
	@Transactional
	@DisplayName("Point fetching test")
	public void tryFindAll_then_insertedAndFoundElementListsAreEqual()
	{
		// testing data to be saved
		List<Point> points = List.of(
				new Point(1, 2, 3),
				new Point(2, 4 ,2),
				new Point(0, 2, 1),
				new Point(2, 2, 2),
				new Point(2, 4 ,3),
				new Point(1, 2, 1)
		);

		// assume, that point hits the desired area
		points.forEach(p -> p.setResult(true));

		// point saving result
		List<Point> savingResult = points.stream()
		                                 .map(pointDao::add)
		                                 .collect(Collectors.toList());

		Assertions.assertEquals(points.size(), pointDao.count(), "Expected another amount of points stored in the database: means that database was not clear or not every point was inserted");
		savingResult.forEach(p -> {
			Assertions.assertNotNull(p, "Point is null: means that point was not inserted.");
			Assertions.assertNotNull(p.getId(), "Point id is null: means that point was not inserted.");
		});

		// find all the points stored in the database
		List<Point> findAllResult = pointDao.findAll();

		// found & inserted points lists should have the same order
		findAllResult.sort(Comparator.comparingLong(Point::getId));
		savingResult.sort(Comparator.comparingLong(Point::getId));

		Assertions.assertEquals(savingResult, findAllResult, "Saving result differs from the findAll result: means that findAll() operation or testing data insertion failed");
	}
}
