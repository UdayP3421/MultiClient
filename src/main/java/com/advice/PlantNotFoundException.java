package com.advice;

public class PlantNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlantNotFoundException(String message) {
		super(message);
	}
}