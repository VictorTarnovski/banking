package com.victor_tarnovski.banking.infra.accounts;

import com.victor_tarnovski.banking.domain.aggregates.Account;
import com.victor_tarnovski.banking.domain.ids.AccountId;
import com.victor_tarnovski.banking.domain.ids.UserId;
import com.victor_tarnovski.banking.infra.value_objects.MoneyMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class AccountMapper {
  public AccountEntity toEntity(final Account account) {
    return AccountEntity.builder()
        .id(account.id().value())
        .initialBalance(MoneyMapper.toEntity(account.initialBalance()))
        .balance(MoneyMapper.toEntity(account.balance()))
        .userId(account.userId().value())
        .build();
  }
  
  public Account toDomain(final AccountEntity entity) {
    var account = new Account(
      new AccountId(entity.id),
      MoneyMapper.toDomain(entity.initialBalance),
      MoneyMapper.toDomain(entity.balance),
      new UserId(entity.id)
    );

    return account;
  }
}