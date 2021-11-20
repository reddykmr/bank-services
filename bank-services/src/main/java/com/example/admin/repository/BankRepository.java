package com.example.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Bank;
@Repository
public interface BankRepository extends  JpaRepository<Bank, String> {

}
