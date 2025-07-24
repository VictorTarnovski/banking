package com.victor_tarnovski.banking.application.repositories;

import com.victor_tarnovski.banking.domain.aggregates.transactions.Transaction;

public interface TransactionRepository {
  Transaction create(final Transaction transaction);
}
