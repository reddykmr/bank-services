package com.example.admin.service;

import com.example.model.AccountRequestInfo;
import com.example.model.AccountResponseInfo;

/*
 * Created by Mahesh Karna
 */
public interface BankService {
	public AccountResponseInfo createAccount(AccountRequestInfo requestInfo);

}
