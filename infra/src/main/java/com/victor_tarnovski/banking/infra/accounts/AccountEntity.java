package com.victor_tarnovski.banking.infra.accounts;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

import com.victor_tarnovski.banking.infra.value_objects.MoneyEntity;

@Entity
@Table(name = "accounts")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public UUID id;
  @AttributeOverrides({
    @AttributeOverride(name = "amount", column = @Column(name = "initial_balance_amount")),
    @AttributeOverride(name = "currency", column = @Column(name = "initial_balance_currency"))
  })
  public MoneyEntity initialBalance;
  @AttributeOverrides({
    @AttributeOverride(name = "amount", column = @Column(name = "balance_amount")),
    @AttributeOverride(name = "currency", column = @Column(name = "balance_currency"))
  })
  public MoneyEntity balance;
  @Column(name = "user_id")
  public UUID userId;
}



