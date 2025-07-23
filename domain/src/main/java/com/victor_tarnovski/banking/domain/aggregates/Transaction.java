package com.victor_tarnovski.banking.domain.aggregates;

import com.victor_tarnovski.banking.domain.ids.TransactionId;
import com.victor_tarnovski.banking.domain.vo.Money;

import java.util.Objects;

import com.victor_tarnovski.banking.domain.ids.WalletId;

public class Transaction {
  private final TransactionId id;
  private final Money amount;
  private final WalletId fromWalletId;
  private final WalletId toWalletId;

  public Transaction(
    final Money amount,
    final WalletId fromWalletId,
    final WalletId toWalletId
  ) {
    this(null, amount, fromWalletId, toWalletId);
  }
  
  public Transaction(
    final TransactionId id,
    final Money amount,
    final WalletId fromWalletId,
    final WalletId toWalletId
  ) {
    this.id = id;
    
    Objects.requireNonNull(amount, "amount must not be null");
    this.amount = amount;

    Objects.requireNonNull(fromWalletId, "fromWalletId must not be null");
    this.fromWalletId = fromWalletId;
    
    Objects.requireNonNull(toWalletId, "toWalletId must not be null");
    this.toWalletId = toWalletId;
  }

  public TransactionId id() {
    return id;
  }

  public Money amount() {
    return amount;
  }
  
  public WalletId fromWalletId() {
    return fromWalletId;
  }

  public WalletId toWalletId() {
    return toWalletId;
  }

}
