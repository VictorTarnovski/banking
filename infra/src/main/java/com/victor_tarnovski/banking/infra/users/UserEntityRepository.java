package com.victor_tarnovski.banking.infra.users;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Named
@ApplicationScoped
public class UserEntityRepository {
  private final EntityManager entityManager;

  @Inject
  public UserEntityRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public UUID newId() {
    var uuid = (UUID) entityManager
        .createNativeQuery("SELECT uuid_v7()")
        .getSingleResult();

    return uuid; 
  }

  @Transactional
  public void save(UserEntity entity) {
    entityManager.persist(entity);
  }

  public Optional<UserEntity> findByEmail(String email) {
    try {
      TypedQuery<UserEntity> query = entityManager
          .createQuery("SELECT u FROM users u WHERE u.email = :email", UserEntity.class);
      query.setParameter("email", email);
      return Optional.of(query.getSingleResult());
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }
}