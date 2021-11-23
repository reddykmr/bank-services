package com.example.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.admin.service.BankService;
import com.example.model.AccountRequestInfo;
import com.example.model.AccountResponseInfo;
import com.example.model.Bank;

/*
 * Created by Mahesh Karna
 */
@RestController
@RequestMapping("/admin")
public class BankController {
	
	@Autowired
	private BankService bankService;
	
	
	
	
	
	@PostMapping("/createAcc")
	public ResponseEntity< AccountResponseInfo> createAccount(@RequestBody AccountRequestInfo requestInfo) {
		   AccountResponseInfo responseInfo=bankService.createAccount(requestInfo);
		        
		return ResponseEntity.status(HttpStatus.OK).body(responseInfo);
		 
		
	}
	@PutMapping("/blockaccount")
	public ResponseEntity<String> blockAccount(@RequestBody Bank bank){
		  String status=bankService.blockAccount(bank);
		  
		  return ResponseEntity.status(HttpStatus.OK).body(status);
		
	}
	@PutMapping("updateaccount")
	public ResponseEntity<Bank> updateAccount(@RequestBody Bank bank){
		  Bank resultbank=bankService.updateAccountDetails(bank);
		  return ResponseEntity.status(HttpStatus.OK).body(resultbank);
		
	}
	

}
