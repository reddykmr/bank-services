package com.example.admin.service;

import com.example.model.Bank;


/*
 * Created by Mahesh Karna
 */
public interface CustomerService {
	   public Bank getAccountDetails(String accno);
	   public String getBalance(String accno);
	   public String depositAmount(String accno,double amount);
	   public Bank transferMoney(String accno1,double amount1, String accno2);
	   public Bank withdrawMoney(String accno,double amount);

}
