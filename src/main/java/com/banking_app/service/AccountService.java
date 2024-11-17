package com.banking_app.service;

import com.banking_app.entity.Account;
import com.banking_app.entity.TransactionHistory;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account);
    List<Account> getAllAccounts();
    Account withDrawAmount(Long accountNumber,Long amount);
    Account depositAmount(Long accountNumber,Long amount);
    boolean deleteAccount(Long accountNumber);
    Account viewAccount(Long accountNumber);
    void transferMoney(Long fromAccountNumber,Long toAccountNumber,Long amount);
    Account viewAccount(Long accountNumber,String accountHolderName);
    boolean emailExists(String email);

    // Add method for fetching transaction history
    List<TransactionHistory> getTransactionHistory(Long accountNumber);
    void recordTransaction(TransactionHistory transactionHistory);  // Add method to record transactions
}
