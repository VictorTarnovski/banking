package com.victor_tarnovski.banking.infra.transactions;

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
@Table(name = "transactions")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public UUID id;
  public MoneyEntity amount;
  @Column(name = "debtor_account_id")
  public UUID debtorAccontId;
  @Column(name = "creditor_account_id")
  public UUID creditorAccountId; 
}