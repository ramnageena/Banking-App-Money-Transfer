package com.banking_app.service;

import com.banking_app.entity.Account;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account);
    List<Account> getAllAccounts();
    Account withDrawAmount(Long accountNumber,Long amount);
    Account depositAmount(Long accountNumber,Long amount);
    boolean deleteAccount(Long accountNumber);
    Account viewAccount(Long accountNumber);
}
