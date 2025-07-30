package com.victor_tarnovski.banking.application.exceptions;

public class UnauthorizedTransferException extends ApplicationException {
  private static final String UNAUTHORIZED_TRANSFER = "unauthorized transfer";

  public UnauthorizedTransferException() {
    super(UNAUTHORIZED_TRANSFER);
  }

  public UnauthorizedTransferException(Throwable cause) {
    super(UNAUTHORIZED_TRANSFER, cause);
  }
}
