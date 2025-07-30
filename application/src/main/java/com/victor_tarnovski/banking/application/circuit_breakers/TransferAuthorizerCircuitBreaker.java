package com.victor_tarnovski.banking.application.circuit_breakers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

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

  private static final Logger log = LoggerFactory.getLogger(TransferAuthorizerCircuitBreaker.class);

  public TransferAuthorizerCircuitBreaker() {
    log.info("TransferAuthorizerCircuitBreaker initialized with state: {}", state);
  }
  
  @Override
  public void authorize(Wallet fromWallet, Wallet toWallet, Money amount) {
    CircuitBreakerState prevState = null;

    if (state == CircuitBreakerState.OPEN) {
      fails++;
      if ((fails % retryAfterEach) == 0) {
        prevState = state;
        state = CircuitBreakerState.HALF_OPEN;
        logStateChange(prevState, state, Level.INFO);
      } else {
        throw new UnauthorizedTransferException(
          new Throwable("circuit breaker is open, cannot authorize transfer")
        );
      }
    }

    if (state == CircuitBreakerState.HALF_OPEN) { 
      try {
        checkAuthorization(fromWallet, toWallet, amount);
        prevState = state;
        state = CircuitBreakerState.CLOSED; 
        logStateChange(prevState, state, Level.INFO);
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
            prevState = state;
            state = CircuitBreakerState.OPEN;
            logStateChange(prevState, state, Level.WARN);
          }
          throw e;
        }
      }
    }

    if (state == CircuitBreakerState.CLOSED) {
      try {
        checkAuthorization(fromWallet, toWallet, amount);
      } catch (UnauthorizedTransferException e) {
        var cause = e.getCause();
        if (
          cause instanceof IOException ||
          cause instanceof InterruptedException
        ) {
          fails++;
          if (fails == maxFails) {
            prevState = state;
            state = CircuitBreakerState.OPEN;
            logStateChange(prevState, state, Level.WARN);
          }
          throw e;
        }
      }
    }

  }

  private void checkAuthorization(Wallet fromWallet, Wallet toWallet, Money amount) {
    var authorizer = new TransferAuthorizerAdapter();
    authorizer.authorize(fromWallet, toWallet, amount);
  }

  private void logStateChange(
    CircuitBreakerState prevState, 
    CircuitBreakerState currState,
    Level level
  ) {
    var message = String.format("TransferAuthorizerCircuitBreaker state changed from {} to {}", prevState, currState);
    log.makeLoggingEventBuilder(level)
      .setMessage(message)
      .log();
  } 

}
