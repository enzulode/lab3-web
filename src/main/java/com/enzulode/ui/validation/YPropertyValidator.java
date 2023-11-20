package com.enzulode.ui.validation;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import lombok.extern.slf4j.Slf4j;

/**
 * This class is responsible for Y form property validation.
 *
 */
@FacesValidator("com.enzulode.ui.validation.YPropertyValidator")
@Slf4j
public class YPropertyValidator implements Validator<Double>
{

	/**
	 * This method validates Y property in JSF form.
	 *
	 * @param context FacesContext for the request we are processing
	 * @param component UIComponent we are checking for correctness
	 * @param value the value to validate
	 * @throws ValidatorException if validation not succeed
	 */
	@Override
	public void validate(FacesContext context, UIComponent component, Double value) throws ValidatorException
	{
		if (value == null)
		{
			log.warn("Validation not succeed: nothing to validate");
			FacesMessage message = new FacesMessage("Y validation failed", "Y cannot be empty");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}

		if ("-0.0".equals(value.toString()))
		{
			log.warn("Validation not succeed: -0.0 is not appropriate");
			FacesMessage message = new FacesMessage("Y validation failed", "-0.0 is not appropriate");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}

		if (Double.compare(value, -5) <= 0 || Double.compare(value, 3) >= 0)
		{
			log.warn("Validation not succeed: Y is out of valid range");
			FacesMessage message = new FacesMessage("Y validation failed", "Y is out of valid range");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}
