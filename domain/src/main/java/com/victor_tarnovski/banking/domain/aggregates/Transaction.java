package com.victor_tarnovski.banking.domain.aggregates;

import com.victor_tarnovski.banking.domain.ids.TransactionId;
import com.victor_tarnovski.banking.domain.vo.Money;

import java.util.Objects;

import com.victor_tarnovski.banking.domain.ids.WalletId;

public class Transaction {
  private final TransactionId id;
  private final Money amount;
  private final WalletId debtorWalletId;
  private final WalletId creditorWalletId;

  public Transaction(
    final Money amount,
    final WalletId debtorWalletId,
    final WalletId creditorWalletId
  ) {
    this(null, amount, debtorWalletId, creditorWalletId);
  }
  
  public Transaction(
    final TransactionId id,
    final Money amount,
    final WalletId debtorWalletId,
    final WalletId creditorWalletId
  ) {
    this.id = id;
    
    Objects.requireNonNull(amount, "amount must not be null");
    this.amount = amount;

    Objects.requireNonNull(debtorWalletId, "debtorWalletId must not be null");
    this.debtorWalletId = debtorWalletId;
    
    Objects.requireNonNull(creditorWalletId, "creditorWalletId must not be null");
    this.creditorWalletId = creditorWalletId;
  }

  public TransactionId id() {
    return id;
  }

  public Money amount() {
    return amount;
  }
  
  public WalletId debtorWalletId() {
    return debtorWalletId;
  }

  public WalletId creditorWalletId() {
    return creditorWalletId;
  }

}
