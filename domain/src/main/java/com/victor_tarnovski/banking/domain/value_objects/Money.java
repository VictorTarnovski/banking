package com.victor_tarnovski.banking.domain.value_objects;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

public class Money implements Comparable<Money> {
  //region fields
  private long amount;
  private Currency currency;
  private static final int[] cents = new int[] { 1, 10, 100, 1000 };
  private static final String CURRENCY_MUST_NOT_BE_NULL = "currency must not be null";

  //endregion

  //region constructors
  public Money(Currency currency) {
    this(0L, currency);
  }

  public Money(long amount, Currency currency) {
    this.amount = amount;

    requireNonNullCurrency(currency);
    this.currency = currency;
  }

  public Money(double amount, Currency currency) {
    this(new BigDecimal(amount), currency);
  }

  public Money(BigDecimal amount, Currency currency) {
    requireNonNullCurrency(currency);
    this.currency = currency;

    this.amount = amount.multiply(new BigDecimal(centFactor())).longValueExact(); 
  }

  public static Money dollars(long amount) {
    return new Money(amount, Currency.getInstance("USD"));
  }
  public static Money dollars(double amount) {
    return new Money(amount, Currency.getInstance("USD"));
  }
  public static Money doolars(BigDecimal amount) {
    return new Money(amount, Currency.getInstance("USD"));
  }
 
  //endregion

  //region getters
  public BigDecimal amount() {
    return BigDecimal.valueOf(this.amount, currency.getDefaultFractionDigits());
  }

  public Currency currency() {
    return currency;
  }

  //endregion

  //region public methods
  public Money plus(Money other) {
    assertSameCurrencyAs(other);
    return newMoney(amount + other.amount);
  }

  public Money subtract(Money other) {
    assertSameCurrencyAs(other);
    return newMoney(amount - other.amount);
  }
  
  public Money multiply(double amount) {
    return multiply(new BigDecimal(amount));
  }

  public Money multiply(BigDecimal amount) {
    return multiply(amount, RoundingMode.HALF_EVEN);
  }
  
  public Money multiply(BigDecimal amount, RoundingMode roundingMode) {
    return new Money(amount().multiply(amount), currency);
  }

  public Money[] allocate(int n) {
    Money lowResult = newMoney(amount / n);
    Money highResult = newMoney(lowResult.amount + 1);
    Money[] results = new Money[n];
    int remainder = (int) amount % n;
    for (int i = 0; i < remainder; i++) results[i] = highResult;
    for (int i = remainder; i < n; i++) results[i] = lowResult;
    return results;
  }

  public Money[] allocate(int[] ratios) {
    long total = 0;
    for (int i = 0; i < ratios.length; i++) total += ratios[i];
    long remainder = amount;
    Money[] results = new Money[ratios.length];
    for (int i = 0; i < results.length; i++) {
      results[i] = newMoney(amount * ratios[i] / total);
      remainder -= results[i].amount;
    }
    for (int i = 0; i < remainder; i++) {
      results[i].amount++;
    }
    return results;
  }

  public boolean greaterThan(Money other) {
    assertSameCurrencyAs(other);
    return compareTo(other) > 0;
  }

  public boolean greaterThanOrEqual(Money other) {
    return greaterThan(other) || equals(other);
  }

  public boolean lessThan(Money other) {
    assertSameCurrencyAs(other);
    return compareTo(other) < 0;
  }

  public boolean lessThanOrEqual(Money other) {
    return lessThan(other) || equals(other);
  }

  //endregion

  //region utility methods
  public boolean equals(Object other) {
    return (other instanceof Money) && equals((Money)other);
  }
  
  public boolean equals(Money other) {
    return compareTo(other) == 0;
  }

  public int hashCode() {
    return (int) (amount ^ (amount >>> 32));
  }

  public int compareTo(Money other) {
    assertSameCurrencyAs(other);
    if (amount < other.amount) return -1;
    else if (amount == other.amount) return 0;
    else return 1;
  }

  //endregion

  //region private methods
  
  private Money newMoney(long amount) {
    return new Money(amount, currency);
  }

  private int centFactor() {
    return cents[currency.getDefaultFractionDigits()];
  }

  private void requireNonNullCurrency(Currency currency) {
    Objects.requireNonNull(currency, CURRENCY_MUST_NOT_BE_NULL);
  }

  private void assertSameCurrencyAs(Money other) {
    assert currency.equals(other.currency) : "money math mismatch";
  }

  //endregion
}
