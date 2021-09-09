package com.realcoderz.exception;

public class ValidationException  extends Exception{

	private String message;
	public ValidationException() {
		
	}
	public ValidationException(String message) {
		super(message);
		this.message=message;
	}

}
