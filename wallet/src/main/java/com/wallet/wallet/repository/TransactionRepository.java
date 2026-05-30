package com.wallet.wallet.repository;

import com.wallet.wallet.entity.Transaction;
import com.wallet.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
