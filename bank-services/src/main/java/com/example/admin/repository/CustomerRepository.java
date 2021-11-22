package com.example.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.Bank;
import com.example.model.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	

}
