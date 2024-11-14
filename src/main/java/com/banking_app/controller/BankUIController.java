package com.banking_app.controller;

import com.banking_app.dto.LoginRequest;
import com.banking_app.dto.TransferRequest;
import com.banking_app.dto.WithDrawRequest;
import com.banking_app.entity.Account;
import com.banking_app.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class BankUIController {

    private final AccountService accountService;

    // Home Page with Navigation
    @GetMapping("/")
    public String homePage() {
        return "home";
    }
    // Contact Page with Navigation
    @GetMapping("/contact")
    String getContact() {
        return "contact";
    }
    // About Page with Navigation
    @GetMapping("/about")
    String getAbout() {
        return "about";
    }
    // Create New Account Page
    // Show the create account page
    @GetMapping("/account/create")
    public String showCreateAccountForm(Model model) {
        model.addAttribute("account", new Account());
        model.addAttribute("message", "Account created successfully!");
        return "createAccount";
    }

    // Handle account creation
    @PostMapping("/account/save")
    public String createAccount(@Valid @ModelAttribute("account")Account account, BindingResult bindingResult, Model model) {
        // Check if email is unique
        if (accountService.emailExists(account.getEmail())) {
            bindingResult.rejectValue("email", "error.account", "This email address is already in use. Please try a different one.!!");
        }

        if (bindingResult.hasErrors()) {
            return "createAccount"; // Return to form with error
        }

        Account createdAccount = accountService.createAccount(account);
        model.addAttribute("account", createdAccount);
        return "account_details"; // Show account details page after creation
    }

    // Account Details Page - Requires Account Number
    @GetMapping("/account/view/{accountNumber}")
    public String viewAccount(@PathVariable Long accountNumber, Model model) {
        Account account = accountService.getAllAccounts().stream()
                .filter(acc -> acc.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElse(null);
        if (account == null) {
            return "account-not-found"; // Error page if account is not found
        }
        model.addAttribute("account", account);
        model.addAttribute("message", "Amount transfer to account : "+account.getAccountNumber()+ " : Successfully !!");
        return "view_account"; // Template for viewing account details
    }


    @GetMapping("/account/view-form")
    public String showViewAccountForm(Model model) {
        model.addAttribute("account",new LoginRequest()); // Empty account object to bind form fields
        return "view_account_form"; // Return the view_account_form.html page
    }


    @PostMapping("/account/validate")
    public String validateLogin(@ModelAttribute LoginRequest loginRequest, Model model) {
        Account account = accountService.viewAccount(loginRequest.getAccountNumber());

        // Check if account exists and name matches
        if (account != null && account.getName().equalsIgnoreCase(loginRequest.getName())) {
            model.addAttribute("account", account);
            model.addAttribute("message", "User logged  in successfully!!!");
            return "view_account"; // Navigate to account details page if login is successful
        } else {
            model.addAttribute("error", "Account number and Name do not matched.");
            return "view_account_form"; // Stay on login page with error message
        }
    }


    @PostMapping("/account/delete/{accountNumber}")
    public String deleteAccount(@PathVariable("accountNumber") Long accountNumber, Model model) {
        boolean deleted = accountService.deleteAccount(accountNumber);
        try {
            if (deleted) {
                model.addAttribute("accountNumber", accountNumber);
                model.addAttribute("message", "Account deleted successfully.");
                return "delete_account_success";
            } else {
                model.addAttribute("errorMessage", "User not found with Account Number: " + accountNumber);
                return "delete_account_error"; // Redirect to error page
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while trying to delete the account.");
            return "delete_account_error"; // Redirect to error page template
        }
    }


    // Withdraw Amount - API call
    @PostMapping("/account/{accountNumber}/withdraw")
    public String withdrawAmount(@PathVariable Long accountNumber, @RequestParam Long amount, Model model) {
        accountService.withDrawAmount(accountNumber, amount);
        return "redirect:/account/" + accountNumber;
    }


    @GetMapping("/account/deposit/{accountNumber}")
    public String showDepositForm(@PathVariable Long accountNumber, Model model) {
        Account account = accountService.viewAccount(accountNumber);
        if (account == null) {
            model.addAttribute("error", "Account not found.");
            return "view_account"; // Return error message if account not found
        }
        model.addAttribute("account", account);
        model.addAttribute("depositRequest", new WithDrawRequest());
        return "deposit"; // Page to show deposit form
    }

    // Handle Deposit
    @PostMapping("/account/deposit/{accountNumber}")
    public String depositAmount(@PathVariable Long accountNumber,
                                @ModelAttribute WithDrawRequest request, Model model) {
        try {
            // Call the service to deposit the amount
            Account updatedAccount = accountService.depositAmount(accountNumber, request.getAmount());
            model.addAttribute("account", updatedAccount);
            model.addAttribute("message", "Amount deposited successfully. New Balance: " + updatedAccount.getBalance());
            return "view_account"; // Redirect to the view account page with updated balance
        } catch (Exception e) {
            model.addAttribute("error", "Error while depositing amount to Account Number: " + accountNumber);
            return "view_account"; // Show error message in case of failure
        }
    }

    @GetMapping("/account/withdraw/{accountNumber}")
    public String showWithDrawForm(@PathVariable Long accountNumber, Model model) {
        Account account = accountService.viewAccount(accountNumber);
        if (account == null) {
            model.addAttribute("error", "Account not found.");
            return "view_account"; // Return error message if account not found
        }
        model.addAttribute("account", account);
        model.addAttribute("depositRequest", new WithDrawRequest());
        return "withdraw"; // Page to show deposit form
    }

    // Handle Deposit
    @PostMapping("/account/withdraw/{accountNumber}")
    public String withdrawAmount(@PathVariable Long accountNumber,
                                 @ModelAttribute WithDrawRequest request, Model model) {
        try {
            // Call the service to deposit the amount
            Account updatedAccount = accountService.withDrawAmount(accountNumber, request.getAmount());
            model.addAttribute("account", updatedAccount);
            model.addAttribute("message", "Amount withdraw successfully. New Balance : " + updatedAccount.getBalance());
            return "view_account"; // Redirect to the view account page with updated balance
        } catch (Exception e) {
            model.addAttribute("error", "Error while withdraw amount to Account Number: " + accountNumber);
            return "view_account"; // Show error message in case of failure
        }
    }

    @GetMapping("/account/transfer/{accountNumber}")
    public String showTransferPage(@PathVariable Long accountNumber, Model model) {
        Account account = accountService.viewAccount(accountNumber);
        model.addAttribute("account", account);
        return "transfer";
    }

    // Handle transfer submission
    @PostMapping("/account/transfer")
    public String transferAmount(@ModelAttribute TransferRequest transferRequest, Model model) {
        try {
            // Perform the transfer
            accountService.transferMoney(
                    transferRequest.getFromAccountNumber(),
                    transferRequest.getToAccountNumber(),
                    transferRequest.getAmount()
            );

            model.addAttribute("message", "Transfer successful!");
            return "redirect:/account/view/" + transferRequest.getFromAccountNumber(); // Redirect to the sender's account details page
        } catch (Exception e) {
            model.addAttribute("error", "Error during transfer: " + e.getMessage());
            return "transfer"; // Stay on the transfer form if there's an error
        }
    }
}
