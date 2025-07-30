package com.victor_tarnovski.banking.domain.ports;

import com.victor_tarnovski.banking.domain.aggregates.Wallet;
import com.victor_tarnovski.banking.domain.vo.Money;

public interface TransferAuthorizer {
  void authorize(Wallet fromWallet, Wallet toWallet, Money amount);
}
