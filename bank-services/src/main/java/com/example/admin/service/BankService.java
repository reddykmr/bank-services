package com.example.admin.service;

import com.example.model.AccountRequestInfo;
import com.example.model.Bank;

public interface BankService {
	public Bank createAccount(AccountRequestInfo requestInfo);

}
