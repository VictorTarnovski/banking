package com.victor_tarnovski.banking.application.circuit_breakers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import com.victor_tarnovski.banking.application.adapters.TransferAuthorizerAdapter;
import com.victor_tarnovski.banking.application.enums.CircuitBreakerState;
import com.victor_tarnovski.banking.domain.aggregates.Wallet;
import com.victor_tarnovski.banking.domain.ports.TransferAuthorizer;
import com.victor_tarnovski.banking.domain.vo.Money;

import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Named
@Singleton
public class TransferAuthorizerCircuitBreaker implements TransferAuthorizer {
  private long fails = 0;
  private final long maxFails = 3;
  private final long retryAfterEach = 5;
  private CircuitBreakerState state = CircuitBreakerState.CLOSED;

  private static final Logger log = LoggerFactory.getLogger(TransferAuthorizerCircuitBreaker.class);

  public TransferAuthorizerCircuitBreaker() {
    log.info("{} initialized with state: {}", getClass().getSimpleName(), state); 
  }

  public void setState(CircuitBreakerState state) {
    var level = Level.INFO;
    
    if (state == CircuitBreakerState.OPEN)
      level = Level.WARN;

    var message = String.format("%s state changed from %s to %s", getClass().getSimpleName(), this.state, state);
    log.makeLoggingEventBuilder(level)
      .setMessage(message)
      .log();

    this.state = state;
  }

  public void incrementFails() {
    fails++;
  }

  public void resetFails() {
    fails = 0;
  }

  public boolean shouldRetry() {
    return (fails % retryAfterEach) == 0;
  }

  public boolean maxFailsReached() {
    return fails == maxFails;
  }

  @Override
  public void authorize(Wallet fromWallet, Wallet toWallet, Money amount) {
    TransferAuthorizerCircuitBreakerState st =  null;

    switch (state) {
      case OPEN: 
        st = new TransferAuthorizerCircuitBreakerOpenState();
        st.execute(this, fromWallet, toWallet, amount);
      
      case HALF_OPEN:
        st = new TransferAuthorizerCircuitBreakerHalfOpenState();
        st.execute(this, fromWallet, toWallet, amount);  
        break;

      case CLOSED:
        st = new TransferAuthorizerCircuitBreakerClosedState();
        st.execute(this, fromWallet, toWallet, amount);
        break;

      default:
        break;
    }
  } 

  void checkAuthorization(Wallet fromWallet, Wallet toWallet, Money amount) {
    var authorizer = new TransferAuthorizerAdapter();
    authorizer.authorize(fromWallet, toWallet, amount);
  }

}
