package com.example.admin.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.admin.repository.BankRepository;
import com.example.admin.repository.CustomerRepository;
import com.example.admin.repository.TransactionLogRepository;
import com.example.exception.AccountBlockedException;
import com.example.exception.InsufficientFundsExcpetion;
import com.example.model.Bank;
import com.example.model.TransactionLogs;
import com.example.utils.AccountStatus;

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
	@Autowired
	private TransactionLogRepository transactionLogRepository;

	/*
	 * GET account details
	 */
	@Override
	public Bank getAccountDetails(String accno) {
		Optional<Bank> bankdetails = bankRepository.findById(accno);
		if (bankdetails.isPresent()) {
			return bankdetails.get();

		} else {
			return null;
		}
	}
	/*
	 * GET account balance
	 */

	@Override
	public String getBalance(String accno) {
		Optional<Bank> bankAccount = bankRepository.findById(accno);

		if (bankAccount.isPresent()) {
			if (AccountStatus.ACTIVATED.getStatus().equalsIgnoreCase(bankAccount.get().getStatus())) {
				double balance = bankAccount.get().getAccountBalance();
				return "Account Balace  :  " + balance;
			} else {
				return "Account is blocked";
			}
		} else {
			return "Account Not Found";
		}
	}
	/*
	 * depositing amount into account
	 */

	@Override
	public String depositAmount(String accno, double amount) {
		Bank bank=getbankDetails(accno);
			if(AccountStatus.ACTIVATED.getStatus().equals(bank.getStatus())) {
				double balance = bank.getAccountBalance();
				if (balance >= 0 && amount > 0) {
					double updatedbalance = balance + amount;
					
					String date=LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss"));

					TransactionLogs logs2 = new TransactionLogs();
					UUID uuid2 = UUID.randomUUID();
					String txid2 = uuid2.toString();
					logs2.setAccno(bank.getAccNo());
					logs2.setAmount(amount);
					logs2.setOpeningbalance(balance);
					logs2.setUpdatedBalance(updatedbalance);
					logs2.setTxid(txid2);
					logs2.setDate(date);
					transactionLogRepository.save(logs2);

					bankRepository.depositeMoney(updatedbalance, accno);
					return "account updated Balance " + updatedbalance;
				} else {
					return "Account is blocked";
				}
			}
		return "account Not updated invalid amount";

	}

	/*
	 * transfer amount into account
	 */
	@Override
	public Bank transferMoney(String accno1, double amount1, String accno2) {
		Optional<Bank> bank1 = bankRepository.findById(accno1);
		Optional<Bank> bank2 = bankRepository.findById(accno2);
		processingTransferRequest(bank1, bank2, amount1);
		bankRepository.findById(accno2);
		return bankRepository.findById(accno1).get();
	}

	/*
	 * withdraw amount from account
	 */
	@Override
	public Bank withdrawMoney(String accno, double amount) {
		Optional<Bank> optionalbank = bankRepository.findById(accno);
		Bank bank = null;
		Bank resultbank = null;
		if (optionalbank.isPresent()) {
			bank = optionalbank.get();
			if (AccountStatus.ACTIVATED.getStatus().equalsIgnoreCase(optionalbank.get().getStatus())) {
				double balance = bank.getAccountBalance();
				if (balance > 0 && amount > 0) {
					double updatedbalance = balance - amount;
					bank.setAccountBalance(updatedbalance);
					String date=LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss"));

					TransactionLogs logs2 = new TransactionLogs();
					UUID uuid2 = UUID.randomUUID();
					String txid2 = uuid2.toString();
					logs2.setAccno(bank.getAccNo());
					logs2.setAmount(amount);
					logs2.setUpdatedBalance(updatedbalance);
					logs2.setOpeningbalance(balance);
					logs2.setTxid(txid2);
					logs2.setDate(date);
					transactionLogRepository.save(logs2);

					resultbank = bankRepository.save(bank);
				} else {
					throw new InsufficientFundsExcpetion("no balance");
				}
			} else {
				throw new AccountBlockedException("Account is blocked");
			}
		}

		return resultbank;
	}

	private void processingTransferRequest(Optional<Bank> bank1, Optional<Bank> bank2, double amount1) {
		if (bank1.isPresent() && bank2.isPresent()) {
			if (AccountStatus.ACTIVATED.getStatus().equalsIgnoreCase(bank1.get().getStatus())
					&& AccountStatus.ACTIVATED.getStatus().equalsIgnoreCase(bank2.get().getStatus())) {
				double balance1 = bank1.get().getAccountBalance();
				double balance2 = bank2.get().getAccountBalance();

				if (balance1 > 0) {
					double updatedbalance2 = balance2 + amount1;
					double updatedbalance1 = balance1 - amount1;
					Bank updateBalance2 = bank2.get();
					Bank updateBalance1 = bank1.get();
					updateBalance2.setAccountBalance(updatedbalance2);
					updateBalance1.setAccountBalance(updatedbalance1);
                    
					String date=LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss"));
					
					TransactionLogs logs1 = new TransactionLogs();
					UUID uuid = UUID.randomUUID();
					String txid = uuid.toString();
					logs1.setAccno(updateBalance2.getAccNo());
					logs1.setAmount(amount1);
					logs1.setUpdatedBalance(updatedbalance2);
					logs1.setOpeningbalance(balance2);
					logs1.setTxid(txid);
					logs1.setDate(date);
					transactionLogRepository.save(logs1);

					TransactionLogs logs2 = new TransactionLogs();
					UUID uuid2 = UUID.randomUUID();
					String txid2 = uuid2.toString();
					logs2.setAccno(updateBalance1.getAccNo());
					logs2.setUpdatedBalance(updatedbalance1);
					logs2.setAmount(amount1);
					logs2.setOpeningbalance(balance1);
					logs2.setTxid(txid2);
					logs2.setDate(date);
					transactionLogRepository.save(logs2);

					bankRepository.save(updateBalance2);
					bankRepository.save(updateBalance1);

				} else {
					throw new InsufficientFundsExcpetion("no balance");
				}
			} else {
				throw new AccountBlockedException("Account is blocked");
			}

		}
	}

	private Bank getbankDetails(String accno) {
		Optional<Bank> optional = bankRepository.findById(accno);
		Bank bank = new Bank();
		if (optional.isPresent()) {
			bank = optional.get();
		}
		return bank;
	}

}
