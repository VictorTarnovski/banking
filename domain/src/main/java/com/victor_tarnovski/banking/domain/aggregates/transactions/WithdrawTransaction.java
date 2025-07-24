package com.victor_tarnovski.banking.domain.aggregates.transactions;

import java.util.Optional;

import com.victor_tarnovski.banking.domain.aggregates.enums.TransactionType;
import com.victor_tarnovski.banking.domain.ids.TransactionId;
import com.victor_tarnovski.banking.domain.ids.WalletId;
import com.victor_tarnovski.banking.domain.vo.Money;

public class WithdrawTransaction extends Transaction {
  public WithdrawTransaction(
    TransactionId id,
    Money amount, 
    WalletId fromWalletId 
  ) {
    super(id, amount, fromWalletId, null, TransactionType.TRANSFER);
    requireNonNullFromWalletId(fromWalletId);
  }
  
  public WithdrawTransaction(
    Money amount, 
    WalletId fromWalletId 
  ) {
    this(null, amount, fromWalletId);
  }

  public WalletId fromWalletId() {
    return fromWalletId;
  }

  public Optional<WalletId> toWalletId() {
    return Optional.empty();
  }

}
