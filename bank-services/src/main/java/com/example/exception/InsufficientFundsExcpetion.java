package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InsufficientFundsExcpetion extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7717126555559763258L;

	public InsufficientFundsExcpetion(String msg) {
		  super(msg);
	}

}
