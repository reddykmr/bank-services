package com.example.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {
	 @Id
	 private int cid;
	 
	 @OneToMany
	 private List<Bank> bankslist;

}
