package com.example.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.TransactionLogs;
@Repository
public interface TransactionLogRepository extends CrudRepository<TransactionLogs, String> {
	@Query("SELECT log FROM TransactionLogs log where log.accno=?1")
	public List<TransactionLogs> getTransactionLogsByAccno(String accno);

}
