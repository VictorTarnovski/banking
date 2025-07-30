package com.victor_tarnovski.banking.application.circuit_breakers;

import java.io.IOException;

import com.victor_tarnovski.banking.application.adapters.TransferAuthorizerAdapter;
import com.victor_tarnovski.banking.application.enums.CircuitBreakerState;
import com.victor_tarnovski.banking.application.exceptions.UnauthorizedTransferException;
import com.victor_tarnovski.banking.domain.aggregates.Wallet;
import com.victor_tarnovski.banking.domain.ports.TransferAuthorizer;
import com.victor_tarnovski.banking.domain.vo.Money;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class TransferAuthorizerCircuitBreaker implements TransferAuthorizer {
  private long fails = 0;
  private final long maxFails = 3;
  private final long retryAfterEach = 5;
  private CircuitBreakerState state = CircuitBreakerState.CLOSED;

  public void authorize(Wallet fromWallet, Wallet toWallet, Money amount) {
    if (state == CircuitBreakerState.OPEN) {
      fails++;
      if ((fails % retryAfterEach) == 0) {
        state = CircuitBreakerState.HALF_OPEN;
      } else {
        throw new UnauthorizedTransferException(
          new Throwable("circuit breaker is open, cannot authorize transfer")
        );
      }
    }

    if (state == CircuitBreakerState.HALF_OPEN) { 
      try {
        var authorizer = new TransferAuthorizerAdapter();
        authorizer.authorize(fromWallet, toWallet, amount);
        state = CircuitBreakerState.CLOSED; 
        fails = 0; 
        return;
      } catch (UnauthorizedTransferException e) {
        var cause = e.getCause();
        if (
          cause instanceof IOException ||
          cause instanceof InterruptedException
        ) {
          fails++;
          if (fails == maxFails) {
            state = CircuitBreakerState.OPEN;
          }
          throw e;
        }
      }
    }

    if (state == CircuitBreakerState.CLOSED) {
      try {
        var authorizer = new TransferAuthorizerAdapter();
        authorizer.authorize(fromWallet, toWallet, amount);
      } catch (UnauthorizedTransferException e) {
        var cause = e.getCause();
        if (
          cause instanceof IOException ||
          cause instanceof InterruptedException
        ) {
          fails++;
          if (fails == maxFails) {
            state = CircuitBreakerState.OPEN;
          }
          throw e;
        }
      }
    }
  }
}
