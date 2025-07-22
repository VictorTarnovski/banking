package com.victor_tarnovski.banking.infra.accounts;

import java.util.Optional;
import java.util.UUID;

import com.victor_tarnovski.banking.infra.EntityRepositoryBase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class AccountEntityRepository extends EntityRepositoryBase<AccountEntity> {
  protected AccountEntityRepository() {
    super();
  }

  @Inject
  public AccountEntityRepository(final EntityManager entityManager) {
    super(entityManager);
  }

  public Optional<AccountEntity> findById(final UUID id) {
    return Optional.ofNullable(entityManager.find(AccountEntity.class, id));
  }
  
  public Optional<AccountEntity> findByUserId(final UUID userId) {
    try {
      var query = entityManager
          .createQuery("SELECT a FROM AccountEntity a WHERE a.userId = :userId", AccountEntity.class);
      query.setParameter("userId", userId);
      return Optional.of(query.getSingleResult());
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }
}
