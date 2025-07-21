package com.victor_tarnovski.banking.infra.value_objects;

import java.util.Currency;

import jakarta.persistence.Embeddable;
import lombok.Builder;

@Embeddable
@Builder
public class MoneyEntity {
  public long amount;
  public Currency currency;
}
