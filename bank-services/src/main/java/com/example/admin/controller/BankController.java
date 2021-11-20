package com.example.admin.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.AccountRequestInfo;

@RestController
public class BankController {
	
	
	@PostMapping("")
	public String createAccount(@RequestBody AccountRequestInfo account) {
		
		return "";
		 
		
	}

}
