package com.victor_tarnovski.banking.infra.value_objects;

import com.victor_tarnovski.banking.domain.value_objects.Money;

public class MoneyMapper {
  public static MoneyEntity toEntity(final Money money) {
    var amount = Money.toLong(money.amount(), money.currency());

    return MoneyEntity.builder()
      .amount(amount)
      .currency(money.currency())  
      .build();
  }

  public static Money toDomain(final MoneyEntity entity) {
    var money = new Money(
      entity.amount,
      entity.currency
    );

    return money;
  }
}
