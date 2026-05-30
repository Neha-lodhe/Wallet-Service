package com.wallet.wallet.controller;
import com.wallet.wallet.service.WalletService;
import com.wallet.wallet.entity.Wallet;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/create/{userId}")
    public Wallet create(@PathVariable Long userId) {
        return walletService.createWallet(userId);
    }

    @GetMapping("/{userId}")
    public Wallet get(@PathVariable Long userId) {
        return walletService.getWallet(userId);
    }

    @PostMapping("/credit/{userId}/{amount}")
    public Wallet credit(
            @PathVariable Long userId,
            @PathVariable BigDecimal amount) {
        return walletService.credit(userId, amount);
    }

    @PostMapping("/debit/{userId}/{amount}")
    public Wallet debit(
            @PathVariable Long userId,
            @PathVariable BigDecimal amount) {
        return walletService.debit(userId, amount);
    }

}
