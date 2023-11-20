package com.enzulode.service;

import com.enzulode.model.PointData;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

/**
 * Default hit-checking service implementation.
 *
 */
@Named("hitCheckingService")
@ApplicationScoped
public class DefaultHitCheckingService implements HitCheckingService
{

	/**
	 * This method performs point area-hit check.
	 *
	 * @param pointData point data
	 * @return hit check result
	 */
	@Override
	public boolean checkHit(PointData pointData)
	{

		int x = pointData.x();
		double y = pointData.y();
		double r = pointData.r();

		double b = r / 2;
		double k = -((double) 1 / (double) 2);

//		cartesian 1
		if (x >= 0 && y >= 0)
			return y <= k*x + b;

//		cartesian 3
		if (x <= 0 && y <= 0)
			return (y * y + x * x) <= r * r;

//		cartesian 4
		if (x >= 0 && y <= 0)
			return (x <= r && y >= -r);

		return false;
	}
}
