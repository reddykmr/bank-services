package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Setter;

@Entity
public class Bank {
	@Id
	private String accNo;
	private String bankName;
	private String type;
	@OneToOne
	private Customer customer;

}
