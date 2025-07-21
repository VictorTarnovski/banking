package com.victor_tarnovski.banking.infra.transactions;

import com.victor_tarnovski.banking.infra.EntityRepositoryBase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;

@Named
@ApplicationScoped
public class TransactionEntityRepository extends EntityRepositoryBase<TransactionEntity> {
  public TransactionEntityRepository(EntityManager entityManager) {
    super(entityManager);
  }
}
  