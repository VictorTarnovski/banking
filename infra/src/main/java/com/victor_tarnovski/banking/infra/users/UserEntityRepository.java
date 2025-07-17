package com.victor_tarnovski.banking.infra.users;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.Optional;

import com.victor_tarnovski.banking.infra.EntityRepositoryBase;

@Named
@ApplicationScoped
public class UserEntityRepository extends EntityRepositoryBase<UserEntity> {
  @Inject
  public UserEntityRepository(EntityManager entityManager) {
    super(entityManager);
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