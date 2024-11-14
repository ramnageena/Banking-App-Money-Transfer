package com.banking_app.service.impl;

import com.banking_app.Repository.AccountRepository;
import com.banking_app.entity.Account;
import com.banking_app.exception.AccountAlreadyExist;
import com.banking_app.exception.AccountNotFoundException;
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
        if (accountRepository.existsByEmail(account.getEmail())) {
            throw new AccountAlreadyExist("Account is already is exists with email :" + account.getEmail());
        }
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

        account.setBalance(account.getBalance() - amount);
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
        log.info("Attempting to delete account with Account Number: {}", accountNumber);

        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account != null) {
            accountRepository.delete(account);
            log.info("Account successfully deleted. Account Number: {}", accountNumber);
            return true;
        } else {
            log.warn("Delete operation failed: Account with Account Number {} not found", accountNumber);
            throw new AccountNotFoundException("Account with account number " + accountNumber + " not found.");
        }
    }


    @Override
    public Account viewAccount(Long accountNumber) {
        log.info("Account getting, Account Number: {}", accountNumber);
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public Account viewAccount(Long accountNumber, String accountHolderName) {
        log.info("Fetching account with Account Number: {} and Account Holder Name: {}", accountNumber, accountHolderName);
        return accountRepository.findByAccountNumberAndName(accountNumber, accountHolderName)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with account number: " + accountNumber + " and account holder name: " + accountHolderName));
    }

    @Transactional
    @Override
    public void transferMoney(Long fromAccountNumber, Long toAccountNumber, Long amount) {
        log.info("Starting money transfer from account: {} to account: {} with amount: {}", fromAccountNumber, toAccountNumber, amount);

        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber);
        if (fromAccount == null) {
            log.error("Sender account not found with account number: {}", fromAccountNumber);
            throw new AccountNotFoundException("Sender account not found.");
        }

        if (fromAccount.getBalance() < amount) {
            log.error("Insufficient funds in sender account. Account number: {}, Available balance: {}, Transfer amount: {}",
                    fromAccountNumber, fromAccount.getBalance(), amount);
            throw new InsufficientException("Insufficient funds in sender account.");
        }

        double remainingAmount = fromAccount.getBalance() - amount;
        fromAccount.setBalance(remainingAmount);
        accountRepository.save(fromAccount);
        log.info("Debited {} from sender account {}. Remaining balance: {}", amount, fromAccountNumber, remainingAmount);

        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber);
        if (toAccount == null) {
            log.error("Receiver account not found with account number: {}", toAccountNumber);
            throw new AccountNotFoundException("Receiver account not found.");
        }

        double totalBalance = toAccount.getBalance() + amount;
        toAccount.setBalance(totalBalance);
        accountRepository.save(toAccount);
        log.info("Credited {} to receiver account {}. New balance: {}", amount, toAccountNumber, totalBalance);

        log.info("Money transfer from account: {} to account: {} completed successfully", fromAccountNumber, toAccountNumber);
    }

    // Check if email exists
    @Override
    public boolean emailExists(String email) {
        return accountRepository.findByEmail(email).isPresent();
    }


}
