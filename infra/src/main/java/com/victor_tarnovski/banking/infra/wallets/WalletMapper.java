package com.victor_tarnovski.banking.infra.wallets;

import com.victor_tarnovski.banking.domain.aggregates.Wallet;
import com.victor_tarnovski.banking.domain.ids.WalletId;
import com.victor_tarnovski.banking.domain.ids.UserId;
import com.victor_tarnovski.banking.infra.value_objects.MoneyMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class WalletMapper {
  public WalletEntity toEntity(final Wallet wallet) {
    var id = wallet.id() != null ? wallet.id().value() : null;
    return WalletEntity.builder()
        .id(id)
        .initialBalance(MoneyMapper.toEntity(wallet.initialBalance()))
        .balance(MoneyMapper.toEntity(wallet.balance()))
        .userId(wallet.userId().value())
        .build();
  }
  
  public Wallet toDomain(final WalletEntity entity) {
    var wallet = new Wallet(
      new WalletId(entity.id),
      entity.initialBalance.currency,
      new UserId(entity.id)
    );

    wallet.credit(MoneyMapper.toDomain(entity.balance));

    return wallet;
  }
}