package com.example.admin.service;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.admin.repository.BankRepository;
import com.example.admin.repository.CustomerRepository;
import com.example.exception.InsufficientFundsExcpetion;
import com.example.model.Bank;


/*
 * Created by Mahesh Karna
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = SQLException.class, noRollbackFor = NullPointerException.class)
public class CistomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private BankRepository bankRepository;

	@Override
	public Bank getAccountDetails(String accno) {
		Optional<Bank> bankdetails = bankRepository.findById(accno);
		if (bankdetails.isPresent()) {
			return bankdetails.get();

		} else {
			return null;
		}
	}

	@Override
	public String getBalance(String accno) {
		Optional<Bank> bankAccount = bankRepository.findById(accno);
		if (bankAccount.isPresent()) {
			double balance = bankAccount.get().getAccoutBalance();
			return "Account Balace  :  " + balance;
		} else {
			return "Account Not Found";
		}
	}

	@Override
	public String depositAmount(String accno, double amount) {
		Optional<Bank> optionalbank = bankRepository.findById(accno);
		Bank bank = null;
		Bank resultbank = null;
		if (optionalbank.isPresent()) {
			bank = optionalbank.get();
			double balance = bank.getAccoutBalance();
			if(balance>0 && amount>0) {
			double updatedbalance = balance +amount;
			bank.setAccoutBalance(updatedbalance);
			resultbank = bankRepository.save(bank);
			return "account updated Balance "+resultbank.getAccoutBalance();
			}
		}
		return "account Not updated invalid amount";
		
		
	}

	@Override
	public Bank transferMoney(String accno1, double amount1, String accno2) {
		Optional<Bank> bank1 = bankRepository.findById(accno1);
		Optional<Bank> bank2 = bankRepository.findById(accno2);
		processingTransferRequest(bank1, bank2, amount1);
		bankRepository.findById(accno2);
		return bankRepository.findById(accno1).get();
	}

	@Override
	public Bank withdrawMoney(String accno, double amount) {
		Optional<Bank> optionalbank = bankRepository.findById(accno);
		Bank bank = null;
		Bank resultbank = null;
		if (optionalbank.isPresent()) {
			bank = optionalbank.get();
			double balance = bank.getAccoutBalance();
			if(balance>0 && amount>0) {
			double updatedbalance = balance - amount;
			bank.setAccoutBalance(updatedbalance);
			resultbank = bankRepository.save(bank);
			}
			else {
				throw new InsufficientFundsExcpetion("no balance");
			}
		}

		return resultbank;
	}

	private void processingTransferRequest(Optional<Bank> bank1, Optional<Bank> bank2, double amount1) {
		if (bank1.isPresent() && bank2.isPresent()) {
			double balance1 = bank1.get().getAccoutBalance();
			double balance2 = bank2.get().getAccoutBalance();

			if (balance1 > 0) {
				double updatedbalance2 = balance2 + amount1;
				double updatedbalance1 = balance1 - amount1;
				Bank updateBalance2 = bank2.get();
				Bank updateBalance1 = bank1.get();
				updateBalance2.setAccoutBalance(updatedbalance2);
				updateBalance1.setAccoutBalance(updatedbalance1);
				bankRepository.save(updateBalance2);
				bankRepository.save(updateBalance1);

			} else {
				throw new InsufficientFundsExcpetion("no balance");
			}

		}
	}

}
