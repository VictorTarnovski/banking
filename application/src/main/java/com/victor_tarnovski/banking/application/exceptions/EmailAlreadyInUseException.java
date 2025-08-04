package com.victor_tarnovski.banking.application.exceptions;

public class EmailAlreadyInUseException extends ApplicationException {

  public EmailAlreadyInUseException() {
    super("e-mail already in use");
  }
  
}
