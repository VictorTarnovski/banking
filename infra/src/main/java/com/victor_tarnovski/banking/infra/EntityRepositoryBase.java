package com.victor_tarnovski.banking.infra;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Named
@ApplicationScoped
public class EntityRepositoryBase<TEntity> {
  protected final EntityManager entityManager;

  @Inject
  public EntityRepositoryBase(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Transactional
  public void save(TEntity entity) {
    entityManager.persist(entity);
  }

}