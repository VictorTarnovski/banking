package com.victor_tarnovski.banking.domain.exceptions;

public class InsufficientBalanceException extends DomainException {
  public InsufficientBalanceException() {
    super("insufficient balance");
  }
}
