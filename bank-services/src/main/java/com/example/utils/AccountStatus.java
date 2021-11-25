package com.example.utils;

public enum AccountStatus {
	
	INACTIVE("INACTIVE"),
	ACTIVATED("ACTIVATE"),
	BLOCKED("BLOCKED");
	
	private String status;
	
	AccountStatus(String status) {
		this.status=status;
	}
	public String getStatus() {
		return status;
	}
	

}
