/**
 * Copyright 2015 Intuit Inc. All rights reserved. Unauthorized reproduction
 * is a violation of applicable law. This material contains certain
 * confidential or proprietary information and trade secrets of Intuit Inc.
 */
package com.voidenterprise.realtor.rest.exception;

/**
 * Models a document not found error.
 * 
 * @author koppenheim
 *
 */
public class ResourceNotFoundException extends ResourceServiceException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs exception without message or cause.
	 */
	public ResourceNotFoundException() {
		super();
	}

	/**
	 * Construct with a message {@code String} that is returned by the inherited
	 * {@code Throwable.getMessage}.
	 * 
	 * @param message
	 *            the message that is returned by the inherited
	 *            {@code Throwable.getMessage}
	 */
	public ResourceNotFoundException(String message) {
		super(message);
	}

	/**
	 * Construct with a {@code Throwable} cause that is returned by the
	 * inherited {@code Throwable.getCause}. The {@code Throwable.getMessage}
	 * will display the output from {@code toString} called on the {@code cause}
	 * .
	 * 
	 * @param cause
	 *            the cause that is returned by the inherited
	 *            {@code Throwable.getCause}
	 */
	public ResourceNotFoundException(Throwable cause) {
		super(cause);
	}

	/**
	 * Construct with both a {@code String} message and a {@code Throwable}
	 * cause. The {@code message} is returned by the inherited
	 * {@code Throwable.getMessage}. The cause that is returned by the inherited
	 * {@code Throwable.getCause}.
	 * 
	 * @param message
	 *            the message that is returned by the inherited
	 *            {@code Throwable.getMessage}
	 * @param cause
	 *            the cause that is returned by the inherited
	 *            {@code Throwable.getCause}
	 */
	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
