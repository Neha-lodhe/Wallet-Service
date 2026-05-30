package com.wallet.wallet.Exception;

public class InsufficientBalanceException extends RuntimeException{

    public InsufficientBalanceException(String msg) {
        super(msg);}
}
