
package com.victor_tarnovski.banking.domain.aggregates;

import com.victor_tarnovski.banking.domain.exceptions.InsufficientBalanceException;
import com.victor_tarnovski.banking.domain.ids.WalletId;
import com.victor_tarnovski.banking.domain.ids.UserId;
import com.victor_tarnovski.banking.domain.vo.Money;

import java.util.Currency;
import java.util.Objects;

public class Wallet {
  private final WalletId id;
  private final Money initialBalance;
  private Money balance;
  private final UserId userId;


  public Wallet(
    Currency currency,  
    UserId userId
  ) {
    this(
      null,
      currency,
      userId
    );
  }


  public Wallet(
    WalletId id,
    Currency currency,
    UserId userId
  ) {
    this.id = id;

    Objects.requireNonNull(currency, "currency must not be null");
    var initialBalance = new Money(currency);
    this.initialBalance = initialBalance;
    this.balance = initialBalance;

    Objects.requireNonNull(userId, "userId must not be null");
    this.userId = userId;
  }


  public WalletId id() {
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

  public void deposit(Money value) {
    Objects.requireNonNull(value, "value must not be null");
    ensureGreaterThanOrEqualZero(value);

    balance = balance.add(value);
  }

  public void withdraw(Money value) {
    Objects.requireNonNull(value, "value must not be null");
    ensureGreaterThanOrEqualZero(value);

    if (!balance.greaterThanOrEqual(value)) {
      throw new InsufficientBalanceException();
    }

    balance = balance.subtract(value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Wallet)) return false;
    var wallet = (Wallet) o;
    return Objects.equals(id, wallet.id);
  }

  private void ensureGreaterThanOrEqualZero(Money value) {
    var zero = new Money(value.currency());
    if (!value.greaterThanOrEqual(zero)) {
      throw new IllegalArgumentException("value must be greater than zero");
    }
  }

}