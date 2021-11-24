package com.example.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TransactionLogs {
	@Id
	private String txid;
	private String accno;
	private String  date;
	private double amount;
	private double updatedBalance;
	private double openingbalance;
	public String getTxid() {
		return txid;
	}
	public void setTxid(String txid) {
		this.txid = txid;
	}
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getUpdatedBalance() {
		return updatedBalance;
	}
	public void setUpdatedBalance(double updatedBalance) {
		this.updatedBalance = updatedBalance;
	}
	public double getOpeningbalance() {
		return openingbalance;
	}
	public void setOpeningbalance(double openingbalance) {
		this.openingbalance = openingbalance;
	}
	

}
