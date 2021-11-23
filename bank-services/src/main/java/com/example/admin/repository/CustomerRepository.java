package com.example.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.Customer;

/*
 * Created by Mahesh Karna
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	
}
