package com.victor_tarnovski.banking.infra.transactions;

import com.victor_tarnovski.banking.domain.aggregates.Transaction;
import com.victor_tarnovski.banking.domain.ids.AccountId;
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
      .debtorAccontId(transaction.debtorAccountId().value())
      .creditorAccountId(transaction.creditorAccountId().value())
      .build();
  }

  public Transaction toDomain(final TransactionEntity entity) {
    var transaction = new Transaction(
      new TransactionId(entity.id),
      MoneyMapper.toDomain(entity.amount),
      new AccountId(entity.debtorAccontId),
      new AccountId(entity.creditorAccountId)
    );

    return transaction;
  }
}
