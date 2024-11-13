/*
package com.banking_app.controller;

import com.banking_app.dto.TransferRequest;
import com.banking_app.dto.WithDrawRequest;
import com.banking_app.entity.Account;
import com.banking_app.exception.AccountNotFoundException;
import com.banking_app.exception.InsufficientException;
import com.banking_app.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
 // Versioned API endpoint
public class BankController {

    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount( @RequestBody Account account) {
        try {
            Account createdAccount = accountService.createAccount(account);
            log.info("New account created successfully with Account Number: {}", createdAccount.getAccountNumber());
            return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating a new account", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        try {
            List<Account> accounts = accountService.getAllAccounts();
            log.info("Fetched all accounts successfully, total accounts: {}", accounts.size());
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error retrieving all accounts", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/withdraw/{accountNumber}")
    public ResponseEntity<Account> withDrawAmount(
            @PathVariable Long accountNumber,
            @RequestBody WithDrawRequest request) {
        try {
            Account updatedAccount = accountService.withDrawAmount(accountNumber, request.getAmount());
            log.info("Amount withdrawn successfully from Account Number: {}, New Balance: {}", accountNumber, updatedAccount.getBalance());
            return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while withdrawing amount from Account Number: {}", accountNumber, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/deposit/{accountNumber}")
    public ResponseEntity<Account> depositAmount(
            @PathVariable Long accountNumber,
            @RequestBody WithDrawRequest request) {
        try {
            Account updatedAccount = accountService.depositAmount(accountNumber, request.getAmount());
            log.info("Amount deposited successfully to Account Number: {}, New Balance: {}", accountNumber, updatedAccount.getBalance());
            return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while depositing amount to Account Number: {}", accountNumber, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{accountNumber}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountNumber) {
        try {
            accountService.deleteAccount(accountNumber);
            log.info("Account deleted successfully, Account Number: {}", accountNumber);
            return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting account with Account Number: {}", accountNumber, e);
            return new ResponseEntity<>("Error while deleting account", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/viewAccount/{accountNumber}")
    public ResponseEntity<Account> viewAccount(@PathVariable Long accountNumber) {
        try {
           Account account= accountService.viewAccount(accountNumber);
            log.info("Account Got successfully, Account Number: {}", accountNumber);
            return new ResponseEntity<>(account, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error getting account with Account Number: {}", accountNumber, e);
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferAmount(@RequestBody TransferRequest request) {
        try {
            log.info("Received transfer request: From account {} to account {}, Amount: {}",
                    request.getFromAccountNumber(), request.getToAccountNumber(), request.getAmount());

            accountService.transferMoney(request.getFromAccountNumber(), request.getToAccountNumber(), request.getAmount());

            log.info("Transfer completed successfully for request: From account {} to account {}",
                    request.getFromAccountNumber(), request.getToAccountNumber());

            return new ResponseEntity<>("Transfer completed successfully.", HttpStatus.OK);
        } catch (AccountNotFoundException ex) {
            log.error("Account not found error: {}", ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (InsufficientException ex) {
            log.error("Insufficient funds error: {}", ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            log.error("Unexpected error during transfer: ", ex);
            return new ResponseEntity<>("Transfer failed due to an internal error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
*/
