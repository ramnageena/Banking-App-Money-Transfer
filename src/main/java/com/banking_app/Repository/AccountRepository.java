package com.banking_app.Repository;

import com.banking_app.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByAccountNumber(Long accountNumber);
}
