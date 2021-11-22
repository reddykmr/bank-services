package com.example.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.admin.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("getBalance/{accno}")
	public String balanceEnquiry(@PathVariable String accno) {
		   return customerService.getBalance(accno);
		
	}
	@PutMapping("deposite/{email}/{amount}/{accno}")
	public String depositeMoney(@PathVariable String email,@PathVariable double amount,@PathVariable String accno) {
		customerService.depositAmount(accno, amount, email);
		return "Amonut deposited";
		
	}

}
