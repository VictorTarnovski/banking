package com.victor_tarnovski.banking.domain.exceptions;

public class InvalidEmailFormatException extends DomainException {
  public InvalidEmailFormatException() {
    super("invalid email");
  }
}
