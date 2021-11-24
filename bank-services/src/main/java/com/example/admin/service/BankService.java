package com.example.admin.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.example.model.AccountRequestInfo;
import com.example.model.AccountResponseInfo;
import com.example.model.Bank;
import com.example.model.TransactionLogs;

/*
 * Created by Mahesh Karna
 */
public interface BankService {
	public AccountResponseInfo createAccount(AccountRequestInfo requestInfo);
	public String blockAccount(Bank bank);
	public Bank updateAccountDetails(Bank bank1);
	public String deleteAccount(String accno);
	public List<TransactionLogs> getLogs(String accno);
	public ByteArrayInputStream downloadPdf(String accno);

}
