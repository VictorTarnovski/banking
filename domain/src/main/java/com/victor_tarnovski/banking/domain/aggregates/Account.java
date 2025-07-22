package com.victor_tarnovski.banking.domain.aggregates;

import com.victor_tarnovski.banking.domain.exceptions.InsufficientBalanceException;
import com.victor_tarnovski.banking.domain.ids.AccountId;
import com.victor_tarnovski.banking.domain.ids.UserId;
import com.victor_tarnovski.banking.domain.value_objects.Money;

import java.util.Currency;
import java.util.Objects;

public class Account {
  private final AccountId id;
  private final Money initialBalance;
  private Money balance;
  private final UserId userId;

  public Account(
    Currency currency,  
    UserId userId
  ) {
    this(
      null,
      currency,
      userId
    );
  }

  public Account(
    AccountId id,
    Currency currency,
    UserId userId
  ) {
    this.id = id;

    Objects.requireNonNull(currency, "currency must not be null");
    var initialBalance = new Money(0, currency);
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

  public Currency currency() {
    return balance.currency();
  }

  public void credit(Money value) {
    balance = balance.add(value);
  }

  public void debit(Money value) {
    if (!balance.greaterThanOrEqual(value)) {
      throw new InsufficientBalanceException();
    }

    balance = balance.subtract(value);
  }
}