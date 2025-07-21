package com.victor_tarnovski.banking.infra.accounts;

import java.util.Optional;
import java.util.UUID;

import com.victor_tarnovski.banking.infra.EntityRepositoryBase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class AccountEntityRepository extends EntityRepositoryBase<AccountEntity> {
  protected AccountEntityRepository() {
    super();
  }

  public AccountEntityRepository(EntityManager entityManager) {
    super(entityManager);
  }

  public Optional<AccountEntity> findById(UUID id) {
    return Optional.ofNullable(entityManager.find(AccountEntity.class, id));
  }
  
}
