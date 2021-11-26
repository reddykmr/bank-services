package com.example.admin.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.admin.repository.BankRepository;
import com.example.model.Bank;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private BankRepository bankRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Bank bank = bankRepository.findByUserName(username);
        return new org.springframework.security.core.userdetails.User(bank.getUserName(), bank.getPassword(), new ArrayList<>());
    }
}
