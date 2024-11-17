package com.banking_app.Repository;

import com.banking_app.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

    List<TransactionHistory> findByAccountNumber(Long accountNumber);
}