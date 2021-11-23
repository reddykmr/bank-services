package com.example.admin.service;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.admin.repository.BankRepository;
import com.example.admin.repository.CustomerRepository;
import com.example.model.AccountRequestInfo;
import com.example.model.AccountResponseInfo;
import com.example.model.Bank;
import com.example.model.Customer;
import com.example.utils.AccountStatus;

/*
 * Created By Mahesh Karna
 */

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {
		SQLException.class }, noRollbackFor = NullPointerException.class)
public class BankServiceImpl implements BankService {

	@Autowired
	private BankRepository bankRepository;

	@Autowired
	private CustomerRepository customerRepository;

	/*
	 * Creating Bank Account
	 */
	@Override
	public AccountResponseInfo createAccount(AccountRequestInfo requestInfo) {
		AccountResponseInfo responseInfo = null;

		if (requestInfo != null) {
			responseInfo = new AccountResponseInfo();
			Customer customer = requestInfo.getCustomerdetails();
			Bank bank = requestInfo.getBankdetails();
			Customer resultcustomer = null;
			Bank resultbank = null;
			resultbank = bankRepository.save(bank);
			resultcustomer = customerRepository.save(customer);
			responseInfo.setBankdetails(resultbank);
			responseInfo.setCustomerdetails(resultcustomer);

		}
		return responseInfo;
	}

	@Override
	public String blockAccount(Bank bank) {

		if (AccountStatus.BLOCKED.getStatus().equalsIgnoreCase(bank.getStatus())) {
			bankRepository.updateAccountStatus(bank.getStatus(), bank.getAccNo());
			return " Account is blocked successfully";
		}

		return "Account is not blocked successfully";

	}

	@Override
	public Bank updateAccountDetails(Bank bank1) {

		Bank resultbank = null;
		if (bank1 != null) {
			resultbank = bankRepository.save(bank1);
		}

		return resultbank;
	}

	@Override
	public String deleteAccount(String accno) {
		Bank bank = getbankDetails(accno);
		if (bank != null) {
			bankRepository.delete(bank);
			return "Account deleted successfully";
		}
		return "Account not found";
	}

	private Bank getbankDetails(String accno) {
		Optional<Bank> optional = bankRepository.findById(accno);
		Bank bank = null;
		if (optional.isPresent()) {
			bank = optional.get();
		}
		return bank;
	}

}
