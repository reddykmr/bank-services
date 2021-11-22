package com.example.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.admin.service.BankService;
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
		   
		   bank.setAccNo("1111234567");
		   bank.setBankName("HDFC");
		   bank.setType("Savings");
		   bank.setAccoutBalance(00.00);
		   bank.setStatus(AccountStatus.ACTIVATED.getStatus());
		   
		   customer.setcId(100);
		   customer.setcName("mahesh");
		   customer.setFname("karna malyadri");
		   customer.setPanNo("HTYPK9826A");
		   customer.setGmail("maheshkarna48@gmail.com");
		   
		   customer.getBanks().add(bank);
		   bank.getCustomers().add(customer);
		   
		   accountRequestInfo.setBankdetails(bank);
		   accountRequestInfo.setCustomerdeatisl(customer);
		   bankService.createAccount(accountRequestInfo);
		   
		return "successfully created";
		 
		
	}

}
