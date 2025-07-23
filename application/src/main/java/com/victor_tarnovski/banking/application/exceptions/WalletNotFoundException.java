package com.victor_tarnovski.banking.application.exceptions;

import com.victor_tarnovski.banking.domain.ids.WalletId;

public class WalletNotFoundException extends NotFoundException {
  public WalletNotFoundException(WalletId id) {
    super("Wallet", id.toString());
  }
}
