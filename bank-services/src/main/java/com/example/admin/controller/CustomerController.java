package com.example.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.admin.service.CustomerService;
import com.example.model.Bank;
import com.example.model.TransferRequest;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("getBalance/{accno}")
	public String balanceEnquiry(@PathVariable String accno) {
		   return customerService.getBalance(accno);
		
	}
	@GetMapping("getAccount/{accno}")
	public ResponseEntity<Bank> getAccount(@PathVariable String accno) {
		    Bank bank=customerService.getAccountDetails(accno);
		   return ResponseEntity.status(HttpStatus.OK).body(bank);
		
	}
	@PutMapping("deposite/{accno}/{amount}")
	public String depositeMoney(@PathVariable String accno,@PathVariable double amount) {
		
		return customerService.depositAmount(accno,amount);
		
	}
	@PutMapping("transfer/{amount}")
	public String tansferMoney(@RequestBody TransferRequest requestInfo,@PathVariable double amount) {
		   Bank bank1=requestInfo.getBank1();
		   Bank bank2=requestInfo.getBank2();
		Bank updatedAccount=customerService.transferMoney(bank1.getAccNo(), amount, bank2.getAccNo());
		return " Updated Account Balance :  "+updatedAccount.getAccoutBalance()+"  Acc No  "+updatedAccount.getAccNo();
		
	}
	@PutMapping("withdraw/{accno}/{amount}")
	public String withdrawMoney(@PathVariable String accno,@PathVariable double amount) {
		       
		Bank updatedAccount=customerService.withdrawMoney(accno, amount);
		return " Updated Account Balance :  "+updatedAccount.getAccoutBalance()+"  Acc No  "+updatedAccount.getAccNo();
		
	}

}
