package com.victor_tarnovski.banking.domain.aggregates.transactions;

import com.victor_tarnovski.banking.domain.aggregates.enums.TransactionType;
import com.victor_tarnovski.banking.domain.ids.TransactionId;
import com.victor_tarnovski.banking.domain.vo.Money;

import java.util.Objects;

import com.victor_tarnovski.banking.domain.ids.WalletId;

public abstract class Transaction {
  private final TransactionId id;
  private final Money amount;
  protected final WalletId fromWalletId;
  protected final WalletId toWalletId;
  private final TransactionType type;

  protected Transaction(
    final Money amount,
    final WalletId fromWalletId,
    final WalletId toWalletId,
    final TransactionType type
  ) {
    this(null, amount, fromWalletId, toWalletId, type);
  }
  
  protected Transaction(
    final TransactionId id,
    final Money amount,
    final WalletId fromWalletId,
    final WalletId toWalletId,
    final TransactionType type
  ) {
    this.id = id;
    
    Objects.requireNonNull(amount, "amount must not be null");
    this.amount = amount;

    this.fromWalletId = fromWalletId;
    this.toWalletId = toWalletId;
  
    Objects.requireNonNull(type, "type must not be null");
    this.type = type;
  }

  public TransactionId id() {
    return id;
  }

  public Money amount() {
    return amount;
  }

  public TransactionType type() {
    return type;
  }

  protected static void requireNonNullFromWalletId(WalletId fromWalletId) {
    Objects.requireNonNull(fromWalletId, "fromWalletId must not be null");
  }

  protected static void requireNonNullToWalletId(WalletId toWalletId) {
    Objects.requireNonNull(toWalletId, "toWalletId must not be null");
  }
}
