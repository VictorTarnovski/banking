package com.victor_tarnovski.banking.domain.exceptions;

public class PasswordMismatchException extends DomainException {
  public PasswordMismatchException() {
    super("password mismatch");
  }
}
