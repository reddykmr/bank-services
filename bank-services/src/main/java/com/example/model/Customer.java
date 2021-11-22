package com.example.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Customer {
	 @Id
	 private int cId;
	 @Column(name = "cname")
	 private String cName;
	 private String panNo;
	 private String fname;
	 private String gmail;
	 @ManyToMany(targetEntity = Bank.class, cascade = CascadeType.ALL)
	 @JoinTable(name="customers_banks",
	            joinColumns = @JoinColumn(referencedColumnName = "cId",name = "customer_id"),
	            inverseJoinColumns = @JoinColumn(referencedColumnName = "accNo",name = "account_no"))       
	 private Set<Bank> banks=new HashSet<Bank>();
	 
	 
	 public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}

	public Set<Bank> getBanks() {
		return banks;
	}
	public void setBanks(Set<Bank> banks) {
		this.banks = banks;
	}
	
	

	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}



	public String getGmail() {
		return gmail;
	}
	public void setGmail(String gmail) {
		this.gmail = gmail;
	}



	public class Address{
		 private int no;
		 private String village;
		 private String landmark;
		 private String pincode;
		public int getNo() {
			return no;
		}
		public void setNo(int no) {
			this.no = no;
		}
		public String getVillage() {
			return village;
		}
		public void setVillage(String village) {
			this.village = village;
		}
		public String getLandmark() {
			return landmark;
		}
		public void setLandmark(String landmark) {
			this.landmark = landmark;
		}
		public String getPincode() {
			return pincode;
		}
		public void setPincode(String pincode) {
			this.pincode = pincode;
		}
		 
	 }
	 public class PersonalDetails{
		 private String fullname;
		 private String mail;
		 private String fname;
		public String getFullname() {
			return fullname;
		}
		public void setFullname(String fullname) {
			this.fullname = fullname;
		}
		public String getMail() {
			return mail;
		}
		public void setMail(String mail) {
			this.mail = mail;
		}
		public String getFname() {
			return fname;
		}
		public void setFname(String fname) {
			this.fname = fname;
		}
		 
	 }
	 

}
