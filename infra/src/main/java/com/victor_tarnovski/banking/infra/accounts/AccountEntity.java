package com.victor_tarnovski.banking.infra.accounts;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

import java.util.Currency;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Builder
public class AccountEntity {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public UUID id;
  @Column(name = "initial_balance_amount")
  public long initialBalanceAmount;
  @Column(name = "initial_balance_currency")
  public Currency initialBalanceCurrency;
  @Column(name = "balance_amount")
  public long balanceAmount;
  @Column(name = "balance_currency")
  public Currency balanceCurrency;
  @Column(name = "user_id")
  public UUID userId;
}



