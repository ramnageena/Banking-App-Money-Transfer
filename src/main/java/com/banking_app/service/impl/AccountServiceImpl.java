package com.banking_app.service.impl;

import com.banking_app.Repository.AccountRepository;
import com.banking_app.entity.Account;
import com.banking_app.exception.InsufficientException;
import com.banking_app.service.AccountService;
import com.banking_app.utils.BankingUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public Account createAccount(Account account) {
        account.setAccountNumber(BankingUtils.random());
        Account savedAccount = accountRepository.save(account);
        log.info("Account created with Account Number: {}", savedAccount.getAccountNumber());
        return savedAccount;
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        log.info("Retrieved {} accounts", accounts.size());
        return accounts;
    }

    @Override
    @Transactional
    public Account withDrawAmount(Long accountNumber, Long amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        log.info("Processing withdrawal for Account Number: {}", accountNumber);

        if (account.getBalance() < amount) {
            log.error("Insufficient funds for Account Number: {}", accountNumber);
            throw new InsufficientException("Insufficient balance");
        }

        account.setBalance(account.getBalance()-amount);
        log.info("Withdrawal successful, new balance for Account Number {}: {}", accountNumber, account.getBalance());
        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public Account depositAmount(Long accountNumber, Long amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        log.info("Processing deposit for Account Number: {}", accountNumber);

        account.setBalance(account.getBalance() + amount);
        log.info("Deposit successful, new balance for Account Number {}: {}", accountNumber, account.getBalance());
        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public boolean deleteAccount(Long accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account!=null) {
            accountRepository.delete(account);
            log.info("Account deleted, Account Number: {}", accountNumber);
            return true;
        } else {
            log.warn("Attempt to delete non-existent Account Number: {}", accountNumber);
            return false;
            //throw new IllegalArgumentException("Account not found");

        //return false;
        }

    }



    @Override
    public Account viewAccount(Long accountNumber) {
        log.info("Account getting, Account Number: {}", accountNumber);
        return accountRepository.findByAccountNumber(accountNumber);
    }
}
