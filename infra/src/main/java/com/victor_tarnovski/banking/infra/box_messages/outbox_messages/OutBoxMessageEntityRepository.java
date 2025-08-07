package com.victor_tarnovski.banking.infra.box_messages.outbox_messages;

import com.victor_tarnovski.banking.infra.EntityRepositoryBase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class OutBoxMessageEntityRepository extends EntityRepositoryBase<OutBoxMessageEntity> {
  protected OutBoxMessageEntityRepository() {
    super();
  }

  @Inject
  public OutBoxMessageEntityRepository(final EntityManager entityManager) {
    super(entityManager);
  }
}
  
