package com.example.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.admin.service.BankService;
import com.example.admin.service.CustomerService;
import com.example.model.AccountRequestInfo;
import com.example.model.Bank;
import com.example.model.Customer;
import com.example.utils.AccountStatus;

@RestController
@RequestMapping("/admin")
public class BankController {
	
	@Autowired
	private BankService bankService;
	
	
	
	
	
	@PostMapping("/createAcc")
	public String createAccount() {
		   AccountRequestInfo accountRequestInfo=new AccountRequestInfo();
		   Bank bank=new Bank();
		   Customer customer=new Customer();
		   
		   bank.setAccNo("1111234568");
		   bank.setBankName("ICICI");
		   bank.setType("Savings");
		   bank.setAccoutBalance(00.00);
		   bank.setStatus(AccountStatus.ACTIVATED.getStatus());
		   
		   customer.setcId(100);
		   customer.setcName("masthan");
		   customer.setFname("burredy masthan");
		   customer.setPanNo("HTYPK9826B");
		   customer.setGmail("masthan188@gmail.com");
		   
		   
		   accountRequestInfo.setBankdetails(bank);
		   accountRequestInfo.setCustomerdetails(customer);
		   bankService.createAccount(accountRequestInfo);
		   
		return "successfully created";
		 
		
	}

}
