package com.victor_tarnovski.banking.domain.aggregates;

import com.victor_tarnovski.banking.domain.ids.AccountId;
import com.victor_tarnovski.banking.domain.ids.UserId;
import com.victor_tarnovski.banking.domain.value_objects.Money;

import java.util.Objects;

public class Account {
  private final AccountId id;
  private final Money initialBalance;
  private final Money balance;
  private final UserId userId;

  public Account(
    Money initialBalance,
    UserId userId
  ) {
    this(
      null,
      initialBalance,
      initialBalance,
      userId
    );
  }

  public Account(
    Money initialBalance,
    Money balance,
    UserId userId
  ) {
    this(
      null,
      initialBalance,
      balance,
      userId
    );
  }

  public Account(
    AccountId id,
    Money initialBalance,
    Money balance,
    UserId userId
  ) {
    this.id = id;

    Objects.requireNonNull(initialBalance, "initialBalance must not be null");
    this.initialBalance = initialBalance;

    Objects.requireNonNull(balance, "balance must not be null");
    this.balance = balance;

    Objects.requireNonNull(userId, "userId must not be null");
    this.userId = userId;
  }

  public AccountId id() {
    return id; 
  }

  public Money initialBalance() {
    return initialBalance;
  }

  public Money balance() {
    return balance;
  }

  public UserId userId() {
    return userId;
  }
}