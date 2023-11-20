package com.enzulode.service;

import com.enzulode.model.PointData;

/**
 * Point hit checking service abstraction.
 *
 */
public interface HitCheckingService
{

	/**
	 * This method performs point area-hit check.
	 *
	 * @param pointData point data
	 * @return hit check result
	 */
	boolean checkHit(PointData pointData);

}
