package com.victor_tarnovski.banking.infra.vo;

import java.util.Currency;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoneyEntity {
  public long amount;
  public Currency currency;
}
