package com.victor_tarnovski.banking.domain.aggregates;

import com.victor_tarnovski.banking.domain.ids.TransactionId;
import com.victor_tarnovski.banking.domain.vo.Money;

import java.util.Objects;

import com.victor_tarnovski.banking.domain.ids.AccountId;

public class Transaction {
  private final TransactionId id;
  private final Money amount;
  private final AccountId debtorAccountId;
  private final AccountId creditorAccountId;

  public Transaction(
    final Money amount,
    final AccountId debtorAccountId,
    final AccountId creditorAccountId
  ) {
    this(null, amount, debtorAccountId, creditorAccountId);
  }
  
  public Transaction(
    final TransactionId id,
    final Money amount,
    final AccountId debtorAccountId,
    final AccountId creditorAccountId
  ) {
    this.id = id;
    
    Objects.requireNonNull(amount, "amount must not be null");
    this.amount = amount;

    Objects.requireNonNull(debtorAccountId, "debtorAccountId must not be null");
    this.debtorAccountId = debtorAccountId;
    
    Objects.requireNonNull(creditorAccountId, "creditorAccountId must not be null");
    this.creditorAccountId = creditorAccountId;
  }

  public TransactionId id() {
    return id;
  }

  public Money amount() {
    return amount;
  }
  
  public AccountId debtorAccountId() {
    return debtorAccountId;
  }

  public AccountId creditorAccountId() {
    return creditorAccountId;
  }

}
