package com.victor_tarnovski.banking.domain.aggregates;

import java.util.Objects;

import com.victor_tarnovski.banking.domain.ids.AccountId;
import com.victor_tarnovski.banking.domain.ids.UserId;
import com.victor_tarnovski.banking.domain.value_objects.Money;

public class Account {
  private final AccountId id;
  private final Money initialBalance;
  private final Money balance;
  private final UserId userId;

  public Account(
    AccountId id,
    Money initialBalance,
    UserId userId
  ) {
    Objects.requireNonNull(id, "id must not be null");
    this.id = id;

    Objects.requireNonNull(initialBalance, "initialBalance must not be null");
    this.initialBalance = initialBalance;
    this.balance = initialBalance;

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