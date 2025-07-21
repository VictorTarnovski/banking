package com.victor_tarnovski.banking.domain.repositories;

import com.victor_tarnovski.banking.domain.aggregates.Transaction;

public interface TransactionRepository {
  Transaction save(Transaction transaction);
}
