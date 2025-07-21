package com.victor_tarnovski.banking.application.repositories;

import com.victor_tarnovski.banking.domain.aggregates.Transaction;

public interface TransactionRepository {
  Transaction save(Transaction transaction);
}
