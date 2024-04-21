package com.phonebooking.exceptions;

public class PhoneNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8698002847635869936L;

	public PhoneNotFoundException(String message) {
		super(message);
	}	
}
