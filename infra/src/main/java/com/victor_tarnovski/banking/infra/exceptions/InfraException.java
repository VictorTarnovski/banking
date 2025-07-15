package com.victor_tarnovski.banking.infra.exceptions;

public class InfraException extends RuntimeException {
  public InfraException(String message) {
    super(message);
  }
}
