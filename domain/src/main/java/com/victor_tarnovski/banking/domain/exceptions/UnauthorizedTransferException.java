package com.victor_tarnovski.banking.domain.exceptions;

public class UnauthorizedTransferException extends  DomainException {
  private static final String UNAUTHORIZED_TRANSFER = "unauthorized transfer";

  public UnauthorizedTransferException() {
    super(UNAUTHORIZED_TRANSFER);
  }

  public UnauthorizedTransferException(Throwable cause) {
    super(UNAUTHORIZED_TRANSFER, cause);
  }
}
