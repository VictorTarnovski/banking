package com.victor_tarnovski.banking.infra;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.UUID;

@Named
@ApplicationScoped
public class EntityRepositoryBase<T> {
  protected final EntityManager entityManager;

  @Inject
  public EntityRepositoryBase(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public UUID newId() {
    var uuid = (UUID) entityManager
        .createNativeQuery("SELECT uuid_v7()")
        .getSingleResult();

    return uuid; 
  }

  @Transactional
  public void save(T entity) {
    entityManager.persist(entity);
  }

}