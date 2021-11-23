package com.example.admin.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Bank;


/*
 * Created by Mahesh Karna
 */
@Repository
public interface BankRepository extends  CrudRepository<Bank, String> {
	@Modifying
	@Query("UPDATE Bank b set b.status=?1 where b.accNo=?2")
	public void updateAccountStatus(String status,String accno);
	@Modifying
	@Query("UPDATE Bank b set b.accountBalance=?1 where b.accNo=?2")
	public void depositeMoney(double amount,String accno);

}