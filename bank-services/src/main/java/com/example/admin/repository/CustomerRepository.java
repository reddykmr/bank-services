package com.example.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.Bank;
@Repository
public interface CustomerRepository extends JpaRepository<Bank, String> {
	@Query("SELECT b FROM Bank b WHERE b.accNo = ?1")
	public Bank getBankAccountByAccNo(String accno);
	@Query("SELECT b FROM Bank b WHERE b.accNo = ?1")
	public Bank getBankAccount(String accno);

}
