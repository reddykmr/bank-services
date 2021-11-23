package com.example.admin.service;

import com.example.model.AccountRequestInfo;
import com.example.model.AccountResponseInfo;
import com.example.model.Bank;

/*
 * Created by Mahesh Karna
 */
public interface BankService {
	public AccountResponseInfo createAccount(AccountRequestInfo requestInfo);
	public String blockAccount(Bank bank);
	public Bank updateAccountDetails(Bank bank1);
	public String deleteAccount(String accno);

}
