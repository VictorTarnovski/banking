package com.victor_tarnovski.banking.infra.wallets;

import java.util.Optional;
import java.util.UUID;

import com.victor_tarnovski.banking.infra.EntityRepositoryBase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class WalletEntityRepository extends EntityRepositoryBase<WalletEntity> {
  protected WalletEntityRepository() {
    super();
  }

  @Inject
  public WalletEntityRepository(final EntityManager entityManager) {
    super(entityManager);
  }

  public Optional<WalletEntity> findById(final UUID id) {
    return Optional.ofNullable(entityManager.find(WalletEntity.class, id));
  }
  
  public Optional<WalletEntity> findByUserId(final UUID userId) {
    try {
      var query = entityManager
          .createQuery("SELECT a FROM WalletEntity a WHERE a.userId = :userId", WalletEntity.class);
      query.setParameter("userId", userId);
      return Optional.of(query.getSingleResult());
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }
}
