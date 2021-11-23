package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.LOCKED)
public class AccountBlockedException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 977069173264007922L;

	public AccountBlockedException(String msg) {
		super(msg);
	}

}
