package com.wallet.wallet.service;

import com.wallet.wallet.entity.Wallet;
import com.wallet.wallet.repository.TransactionRepository;
import com.wallet.wallet.repository.WalletRepository;
import org.springframework.stereotype.Service;
import com.wallet.wallet.entity.Transaction;
import com.wallet.wallet.Exception.InsufficientBalanceException;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public WalletService(WalletRepository walletRepository, TransactionRepository transactionRepository)
    {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    public Wallet createWallet(Long userId) {
        Wallet wallet = new Wallet();
        wallet.setUserId(userId);
        wallet.setBalance(BigDecimal.ZERO);
        return walletRepository.save(wallet);
    }

    public Wallet getWallet(Long userId) {
        return walletRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

    @Transactional
    public Wallet credit(Long userId, BigDecimal amount) {

        Wallet wallet = getWallet(userId);
        wallet.setBalance(wallet.getBalance().add(amount));
        walletRepository.save(wallet);

        saveTransaction(wallet.getId(), amount, "CREDIT");

        return wallet;
    }

    @Transactional
    public Wallet debit(Long userId, BigDecimal amount) {

        Wallet wallet = getWallet(userId);

        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        wallet.setBalance(wallet.getBalance().subtract(amount));
        walletRepository.save(wallet);

        saveTransaction(wallet.getId(), amount, "DEBIT");

        return wallet;
    }

    private void saveTransaction(
            Long walletId,
            BigDecimal amount,
            String type) {

        Transaction tx = new Transaction();
        tx.setWalletId(walletId);
        tx.setAmount(amount);
        tx.setType(type);
        tx.setCreatedAt(LocalDateTime.now());

        transactionRepository.save(tx);
    }
}
