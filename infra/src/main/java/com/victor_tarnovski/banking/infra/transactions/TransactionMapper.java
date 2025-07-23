package com.victor_tarnovski.banking.infra.transactions;

import com.victor_tarnovski.banking.domain.aggregates.Transaction;
import com.victor_tarnovski.banking.domain.ids.WalletId;
import com.victor_tarnovski.banking.domain.ids.TransactionId;
import com.victor_tarnovski.banking.infra.value_objects.MoneyMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class TransactionMapper {
  public TransactionEntity toEntity(final Transaction transaction) {
    var id = transaction.id() != null ? transaction.id().value() : null;
    return TransactionEntity.builder()
      .id(id)
      .amount(MoneyMapper.toEntity(transaction.amount()))
      .debtorWalletId(transaction.debtorWalletId().value())
      .creditorWalletId(transaction.creditorWalletId().value())
      .build();
  }

  public Transaction toDomain(final TransactionEntity entity) {
    var transaction = new Transaction(
      new TransactionId(entity.id),
      MoneyMapper.toDomain(entity.amount),
      new WalletId(entity.debtorWalletId),
      new WalletId(entity.creditorWalletId)
    );

    return transaction;
  }
}
