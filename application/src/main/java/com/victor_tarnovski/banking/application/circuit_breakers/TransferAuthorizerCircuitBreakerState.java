package com.victor_tarnovski.banking.application.circuit_breakers;

import com.victor_tarnovski.banking.domain.aggregates.Wallet;
import com.victor_tarnovski.banking.domain.vo.Money;

public interface TransferAuthorizerCircuitBreakerState {
    void execute(
        TransferAuthorizerCircuitBreaker cb,
        Wallet fromWallet, 
        Wallet toWallet, 
        Money amount
    );
}
