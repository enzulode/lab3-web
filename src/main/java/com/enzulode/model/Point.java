package com.enzulode.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * This class is a domain model of the application.
 *
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "point")
public class Point
{

	/**
	 * Point id.
	 *
	 */
	@Id
	@SequenceGenerator(
			name = "point_id_generator",
			sequenceName = "point_id_seq",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "point_id_generator"
	)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	/**
	 * Point x coordinate.
	 *
	 */
	@Column(name = "x", nullable = false)
	private int x;

	/**
	 * Point y coordinate.
	 *
	 */
	@Column(name = "y", nullable = false)
	private double y;

	/**
	 * Radius.
	 *
	 */
	@Column(name = "r", nullable = false)
	private double r;

	/**
	 * Result.
	 *
	 */
	@Column(name = "result", nullable = false)
	private boolean result;

	/**
	 * Point constructor with coordinates only.
	 *
	 * @param x point x coordinate
	 * @param y point y coordinate
	 * @param r radius
	 */
	public Point(int x, double y, double r)
	{
		this.x = x;
		this.y = y;
		this.r = r;
	}

	/**
	 * This method fetches an immutable point data ({@link PointData} instance)
	 *
	 * @return {@link PointData} instance
	 */
	public PointData getPointData()
	{
		return new PointData(x, y, r);
	}
}
