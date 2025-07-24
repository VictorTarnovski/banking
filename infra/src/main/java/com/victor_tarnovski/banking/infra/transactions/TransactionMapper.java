package com.victor_tarnovski.banking.infra.transactions;

import java.util.UUID;

import com.victor_tarnovski.banking.domain.aggregates.transactions.Transaction;
import com.victor_tarnovski.banking.domain.aggregates.transactions.TransferTransaction;
import com.victor_tarnovski.banking.domain.aggregates.transactions.WithdrawTransaction;
import com.victor_tarnovski.banking.domain.aggregates.transactions.DepositTransaction;
import com.victor_tarnovski.banking.domain.ids.TransactionId;
import com.victor_tarnovski.banking.domain.ids.WalletId;
import com.victor_tarnovski.banking.infra.value_objects.MoneyMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class TransactionMapper {
  public TransactionEntity toEntity(final Transaction transaction) {
    var id = 
      transaction.id() != null 
      ? transaction.id().value() 
      : null;
    
    var fromWalletId = fromWalletId(transaction);
    var toWalletId = toWalletId(transaction);

    return TransactionEntity.builder()
      .id(id)
      .amount(MoneyMapper.toEntity(transaction.amount()))
      .fromWalletId(fromWalletId)
      .toWalletId(toWalletId)
      .type(transaction.type())
      .build();
  }

  public Transaction toDomain(final TransactionEntity entity) {
    var id = 
      entity.id != null 
      ? new TransactionId(entity.id) 
      : null;

    var amount = MoneyMapper.toDomain(entity.amount);

    var fromWalletId = 
      entity.fromWalletId != null
      ? new WalletId(entity.fromWalletId)
      : null;

    var toWalletId =
      entity.toWalletId != null
      ? new WalletId(entity.toWalletId)
      : null;

    Transaction transaction = null;

    switch (entity.type) {
      case DEPOSIT:
        transaction = new DepositTransaction(
          id,
          amount,
          toWalletId
        );
        break;

      case WITHDRAW:
        transaction = new WithdrawTransaction(
          id,
          amount,
          fromWalletId
        );
        break;

      case TRANSFER: 
        transaction = new TransferTransaction(
          id,
          amount,
          fromWalletId,
          toWalletId
        );
        break;
    }

    return transaction;
  }

  private UUID fromWalletId(Transaction transaction) {
    UUID id = null;

    switch (transaction.type()) {
      case WITHDRAW:
        id = ((WithdrawTransaction) transaction).fromWalletId().value();
        break;
      
      case TRANSFER:
        id = ((TransferTransaction) transaction).fromWalletId().value();
        break;

      default:
        break;
    }

    return id;
  }

  private UUID toWalletId(Transaction transaction) {
    UUID id = null;

    switch (transaction.type()) {
      case DEPOSIT:
        id = ((DepositTransaction) transaction).toWalletId().value();
        break;

      case TRANSFER:
        id = ((TransferTransaction) transaction).toWalletId().value();
        break;

      default:
        break;
    }

    return id;
  }
}
