package com.victor_tarnovski.banking.application.exceptions;

import com.victor_tarnovski.banking.domain.ids.AccountId;

public class AccountNotFoundException extends NotFoundException {
  public AccountNotFoundException(AccountId id) {
    super("Acount", id.toString());
  }
  
}
