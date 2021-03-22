package com.customerfarm.springboot.exceptions;

public class FarmNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FarmNotFoundException(String message) {
		super(message);
	}
}
