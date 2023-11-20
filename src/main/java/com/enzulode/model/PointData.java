package com.enzulode.model;

/**
 * This record contains immutable data about point coordinates.
 *
 * @param x point x coordinate
 * @param y point y coordinate
 * @param r radius
 */
public record PointData(int x, double y, double r)
{
}
