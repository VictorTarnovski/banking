package com.victor_tarnovski.banking.infra.transactions;

import com.victor_tarnovski.banking.infra.EntityRepositoryBase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class TransactionEntityRepository extends EntityRepositoryBase<TransactionEntity> {
  protected TransactionEntityRepository() {
    super();
  }

  @Inject
  public TransactionEntityRepository(EntityManager entityManager) {
    super(entityManager);
  }
}
  