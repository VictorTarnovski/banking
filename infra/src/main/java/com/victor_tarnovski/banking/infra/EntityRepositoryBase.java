package com.victor_tarnovski.banking.infra;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Dependent
public class EntityRepositoryBase<TEntity> {
  protected final EntityManager entityManager;

  protected EntityRepositoryBase() {
    this.entityManager = null;
  }

  @Inject
  public EntityRepositoryBase(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Transactional
  public void save(TEntity entity) {
    entityManager.persist(entity);
  }

}