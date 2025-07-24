package com.victor_tarnovski.banking.domain.exceptions;

public class RecursiveTransferException extends DomainException {

  public RecursiveTransferException() {
    super("recursive transfer");
  }
  
}
