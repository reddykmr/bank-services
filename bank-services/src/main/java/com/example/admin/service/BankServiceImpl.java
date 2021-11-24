package com.example.admin.service;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.admin.repository.BankRepository;
import com.example.admin.repository.CustomerRepository;
import com.example.admin.repository.TransactionLogRepository;
import com.example.model.AccountRequestInfo;
import com.example.model.AccountResponseInfo;
import com.example.model.Bank;
import com.example.model.Customer;
import com.example.model.TransactionLogs;
import com.example.pdf.PdfGenerator;
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
	@Autowired
	private TransactionLogRepository transactionLogRepository;
	@Autowired
	private PdfGenerator pdfgenerator;

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
		Bank getbank = getbankDetails(bank1.getAccNo());
		Bank resultbank = new Bank();
		if (bank1 != null) {
			bank1.setAccountBalance(getbank.getAccountBalance());
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

	/*
	 * @Override public String storeTransactionLogs(Bank bank) {
	 * 
	 * Bank getbank = getbankDetails(bank.getAccNo()); double updatebalance =
	 * getbank.getAccountBalance() + bank.getAccountBalance(); UUID txid =
	 * UUID.randomUUID(); String tx = txid.toString();
	 * 
	 * MapSqlParameterSource parameterSource = new MapSqlParameterSource();
	 * parameterSource.addValue("transactionid", tx);
	 * parameterSource.addValue("date", new Date());
	 * parameterSource.addValue("accno", bank.getAccNo());
	 * parameterSource.addValue("updatebalance", updatebalance);
	 * 
	 * int count = jdbcTemplate.update(TransactionsConstants.
	 * UPDATE_TRANSACTIONS_EVERY_TRANSACTION, parameterSource); if (count != 0) {
	 * return "Transaction log successed"; } return null;
	 * 
	 * }
	 */
	@Override
	public List<TransactionLogs> getLogs(String accno) {

		return transactionLogRepository.getTransactionLogsByAccno(accno);
	}

	@Override
	public ByteArrayInputStream downloadPdf(String accno) {
		List<TransactionLogs> list=getLogs(accno);
		return PdfGenerator.getLogs(list);
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
