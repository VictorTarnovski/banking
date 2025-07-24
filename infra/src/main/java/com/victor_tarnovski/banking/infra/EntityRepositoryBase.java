package com.victor_tarnovski.banking.infra;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

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

  public void create(TEntity entity) {
    entityManager.merge(entity);
  }

  public void update(TEntity entity) {
    entityManager.merge(entity);
  }
}