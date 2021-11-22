package com.example.admin.service;

import com.example.model.Bank;

public interface CustomerService {
	   public Bank getAccountDetails(String accno);
	   public String getBalance(String accno);
	   public String depositAmount(String accno,double amount,String email);

}
