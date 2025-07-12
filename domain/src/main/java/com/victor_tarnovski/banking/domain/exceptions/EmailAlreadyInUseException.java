package com.victor_tarnovski.banking.domain.exceptions;

public class EmailAlreadyInUseException extends DomainException {

  public EmailAlreadyInUseException() {
    super("e-mail already in use");
  }
  
}
