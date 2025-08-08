package com.victor_tarnovski.banking.infra.box_messages.outbox_messages;

import java.util.List;

import com.victor_tarnovski.banking.infra.EntityRepositoryBase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class OutBoxMessageEntityRepository extends EntityRepositoryBase<OutBoxMessageEntity> {
  protected OutBoxMessageEntityRepository() {
    super();
  }

  @Inject
  public OutBoxMessageEntityRepository(final EntityManager entityManager) {
    super(entityManager);
  }

  public List<OutBoxMessageEntity> findUnprocessedByType(String type, int limit) {
    TypedQuery<OutBoxMessageEntity> query = entityManager
        .createQuery("SELECT out FROM OutBoxMessageEntity out WHERE out.processedAt is null", OutBoxMessageEntity.class);
    return query.getResultList();
  }
}
  
