package com.enzulode.ui.model;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.*;

import java.io.Serializable;

/**
 * This class represents current form data.
 *
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Named("formData")
@SessionScoped
public class FormData implements Serializable
{
	/**
	 * Current x.
	 *
	 */
	private int x;

	/**
	 * Current y.
	 *
	 */
	private double y;

	/**
	 * Current radius.
	 *
	 */
	private double r;
}
