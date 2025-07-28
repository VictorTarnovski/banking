package com.victor_tarnovski.banking.domain.aggregates.transactions;

import java.util.Optional;

import com.victor_tarnovski.banking.domain.enums.TransactionType;
import com.victor_tarnovski.banking.domain.ids.TransactionId;
import com.victor_tarnovski.banking.domain.ids.WalletId;
import com.victor_tarnovski.banking.domain.vo.Money;

public class DepositTransaction extends Transaction {
  public DepositTransaction(
    TransactionId id,
    Money amount, 
    WalletId toWalletId 
  ) {
    super(id, amount, null, toWalletId, TransactionType.DEPOSIT);
    requireNonNullToWalletId(toWalletId);
  }
  
  public DepositTransaction(
    Money amount, 
    WalletId toWalletId 
  ) {
    this(null, amount, toWalletId);
  }

  public Optional<WalletId> fromWalletId() {
    return Optional.empty();
  }

  public WalletId toWalletId() {
    return toWalletId; 
  }

}
