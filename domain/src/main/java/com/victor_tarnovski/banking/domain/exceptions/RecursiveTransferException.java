package com.victor_tarnovski.banking.domain.exceptions;

public class RecursiveTransferException extends DomainException {

  public RecursiveTransferException() {
    super("recursive transfer: paying account cannot be the same as the receiving account");
  }
  
}
