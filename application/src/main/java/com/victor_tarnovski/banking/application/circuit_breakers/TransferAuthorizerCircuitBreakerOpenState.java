package com.victor_tarnovski.banking.application.circuit_breakers;

import com.victor_tarnovski.banking.application.enums.CircuitBreakerState;
import com.victor_tarnovski.banking.domain.aggregates.Wallet;
import com.victor_tarnovski.banking.domain.exceptions.UnauthorizedTransferException;
import com.victor_tarnovski.banking.domain.vo.Money;

public class TransferAuthorizerCircuitBreakerOpenState implements TransferAuthorizerCircuitBreakerState {
    @Override
    public void execute(TransferAuthorizerCircuitBreaker cb, Wallet fromWallet, Wallet toWallet, Money amount) {
        if (cb.shouldRetry()) {
            cb.setState(CircuitBreakerState.HALF_OPEN);
            return;
        }

        cb.incrementFails();
        throw new UnauthorizedTransferException(
            new Throwable("circuit breaker is open, cannot authorize transfer")
        );
    }
}
