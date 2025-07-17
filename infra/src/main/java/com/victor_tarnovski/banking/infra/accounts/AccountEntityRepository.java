package com.victor_tarnovski.banking.infra.accounts;

import com.victor_tarnovski.banking.infra.EntityRepositoryBase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;

@Named
@ApplicationScoped
public class AccountEntityRepository extends EntityRepositoryBase<AccountEntity> {
  public AccountEntityRepository(EntityManager entityManager) {
    super(entityManager);
  }
  
}
