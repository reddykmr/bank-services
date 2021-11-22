package com.example.admin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Bank;
@Repository
public interface BankRepository extends  CrudRepository<Bank, String> {

}