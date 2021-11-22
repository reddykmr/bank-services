package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Bank {
	@Id
	private String accNo;
	private String bankName;
	private String type;
	private String status;
	private double accountBalance;
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public double getAccoutBalance() {
		return accountBalance;
	}
	public void setAccoutBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	
	

}
