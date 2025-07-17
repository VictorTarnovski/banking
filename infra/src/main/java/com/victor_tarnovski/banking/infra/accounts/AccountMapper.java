package com.victor_tarnovski.banking.infra.accounts;

import com.victor_tarnovski.banking.domain.aggregates.Account;
import com.victor_tarnovski.banking.domain.ids.AccountId;
import com.victor_tarnovski.banking.domain.ids.UserId;
import com.victor_tarnovski.banking.domain.value_objects.Money;

public class AccountMapper {
  public AccountEntity toEntity(final Account account) {

    var initialBalanceAmount = Money.toLong(
      account.initialBalance().amount(), 
      account.initialBalance().currency());
    
    var balanceAmount = Money.toLong(
      account.balance().amount(), 
      account.balance().currency());

    return AccountEntity.builder()
        .id(account.id().value())
        .initialBalanceAmount(initialBalanceAmount)
        .initialBalanceCurrency(account.initialBalance().currency())
        .balanceAmount(balanceAmount)
        .balanceCurrency(account.balance().currency())
        .userId(account.userId().value())
        .build();
  }
  
  public Account toDomain(final AccountEntity entity) {
    var initialBalance = new Money(
      entity.initialBalanceAmount,
      entity.initialBalanceCurrency
    );

    var balance = new Money(
      entity.balanceAmount,
      entity.balanceCurrency
    );

    var account = new Account(
      new AccountId(entity.id),
      initialBalance,
      balance,
      new UserId(entity.id)
    );

    return account;
  }
}