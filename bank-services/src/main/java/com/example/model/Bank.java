package com.example.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Bank {
	@Id
	private String accNo;
	private String bankName;
	private String type;
	private String status;
	private double accountBalance;
	@ManyToMany(targetEntity =Customer.class,cascade = CascadeType.ALL,mappedBy = "banks")
	private Set<Customer> customers =new HashSet<>();
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
	public Set<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}
	
	
	

}
