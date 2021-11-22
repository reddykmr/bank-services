package com.example.admin.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.admin.repository.CustomerRepository;
import com.example.model.Bank;
import com.example.model.Customer;

@Service
@Transactional(propagation = Propagation.REQUIRED,readOnly =false,rollbackFor = SQLException.class,noRollbackFor = NullPointerException.class)
public class CistomerServiceImpl implements CustomerService{
   @Autowired
   private CustomerRepository customerRepository;
   
   
   @Override
	public Bank getAccountDetails(String accno) {
		   Optional<Bank> bankdetails=customerRepository.findById(accno);
		   if(bankdetails.isPresent()) {
			    return bankdetails.get();
			   
			    }
			    else {
			    	return null;
			    }
	}
   
	@Override
	public String getBalance(String accno) {
		    Bank bankAccount=customerRepository.getBankAccountByAccNo(accno);
		    if(bankAccount!=null) {
		    double balance=bankAccount.getAccoutBalance();
		    return "Account Balace  :  "+balance;
		    }
		    else {
		    	return"Account Not Found";
		    }
	}

	@Override
	public String depositAmount(String accno,double amount,String email) {
		    Bank accountDetails=getAccountDetails(accno);
		     List<String> customers=accountDetails.getCustomers().stream()
		                                  .map(Customer::getGmail).collect(Collectors.toList());
		    if(email.equalsIgnoreCase(customers.get(0))) {
		    	 customerRepository.save(accountDetails);
		    }
		
		return null;
	}

}
