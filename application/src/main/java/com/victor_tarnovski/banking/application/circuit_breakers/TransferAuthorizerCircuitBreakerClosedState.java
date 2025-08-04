package com.victor_tarnovski.banking.application.circuit_breakers;

import java.io.IOException;

import com.victor_tarnovski.banking.application.enums.CircuitBreakerState;
import com.victor_tarnovski.banking.domain.aggregates.Wallet;
import com.victor_tarnovski.banking.domain.exceptions.UnauthorizedTransferException;
import com.victor_tarnovski.banking.domain.vo.Money;

public class TransferAuthorizerCircuitBreakerClosedState implements TransferAuthorizerCircuitBreakerState {
    @Override
    public void execute(TransferAuthorizerCircuitBreaker cb, Wallet fromWallet, Wallet toWallet, Money amount) {
        try {
            cb.checkAuthorization(fromWallet, toWallet, amount);
        } catch (UnauthorizedTransferException e) {
            var cause = e.getCause();
            if (
                cause instanceof IOException ||
                cause instanceof InterruptedException
            ) {
                cb.incrementFails();
                if (cb.maxFailsReached())
                    cb.setState(CircuitBreakerState.OPEN);
                throw e;
            }
        }
    }
}
