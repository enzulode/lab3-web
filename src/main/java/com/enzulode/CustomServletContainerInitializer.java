package com.enzulode;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

import java.util.Set;

/**
 * This class is responsible for servlet container initialization.
 *
 */
public class CustomServletContainerInitializer implements ServletContainerInitializer
{

	/**
	 * This method is responsible for application servlet container initialization.
	 *
	 * @param c the Set of application classes that extend, implement, or have been annotated with the class types specified
	 * by the {@link jakarta.servlet.annotation.HandlesTypes HandlesTypes} annotation, or <tt>null</tt> if there are no
	 * matches, or this <tt>ServletContainerInitializer</tt> has not been annotated with <tt>HandlesTypes</tt>
	 *
	 * @param ctx the <tt>ServletContext</tt> of the web application that is being started and in which the classes
	 * contained in <tt>c</tt> were found
	 */
	@Override
	public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException
	{
	}
}
