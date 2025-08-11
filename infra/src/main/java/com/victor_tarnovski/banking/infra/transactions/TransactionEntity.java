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

import com.victor_tarnovski.banking.domain.enums.TransactionType;
import com.victor_tarnovski.banking.infra.vo.MoneyEntity;

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
  @Column(name = "from_wallet_id")
  public UUID fromWalletId;
  @Column(name = "to_wallet_id")
  public UUID toWalletId; 
  @Column(name = "type")
  public TransactionType type;
}