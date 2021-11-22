package com.example.admin.service;



import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.admin.repository.BankRepository;
import com.example.admin.repository.CustomerRepository;
import com.example.model.AccountRequestInfo;
import com.example.model.Bank;
import com.example.model.Customer;

@Service
@Transactional(propagation = Propagation.REQUIRED,readOnly = false,rollbackFor = {SQLException.class},noRollbackFor = NullPointerException.class)
public class BankServiceImpl implements BankService {
	
	@Autowired
	private BankRepository bankRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	

	@Override
	public String createAccount(AccountRequestInfo requestInfo) {
		if(requestInfo!=null) {
			  Customer customer=requestInfo.getCustomerdetails();
		      Bank bank=requestInfo.getBankdetails(); 
		      bankRepository.save(bank);
		      customerRepository.save(customer);
		      return "account created successfully" ;
		}
		return "Data not found";
	}

}
