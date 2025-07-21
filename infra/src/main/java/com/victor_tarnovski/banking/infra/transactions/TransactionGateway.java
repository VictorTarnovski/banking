package com.victor_tarnovski.banking.infra.transactions;

import com.victor_tarnovski.banking.domain.aggregates.Transaction;
import com.victor_tarnovski.banking.domain.repositories.TransactionRepository;

public class TransactionGateway implements TransactionRepository {
  private final TransactionEntityRepository repository;
  private final TransactionMapper mapper;
  
  public TransactionGateway(
    final TransactionEntityRepository repository,
    final TransactionMapper mapper
  ) {
    this.repository = repository;
    this.mapper = mapper;
  }
  
  @Override
  public Transaction save(Transaction transaction) {
    var entity = mapper.toEntity(transaction);
    repository.save(entity);
    return mapper.toDomain(entity); 
  }
  
}
