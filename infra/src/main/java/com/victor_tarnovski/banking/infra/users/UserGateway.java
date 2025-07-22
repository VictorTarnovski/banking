package com.victor_tarnovski.banking.infra.users;

import java.util.Optional;

import com.victor_tarnovski.banking.application.repositories.UserRepository;
import com.victor_tarnovski.banking.domain.aggregates.User;
import com.victor_tarnovski.banking.domain.ids.UserId;
import com.victor_tarnovski.banking.domain.value_objects.Email;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class UserGateway implements UserRepository {
  private final UserEntityRepository repository;
  private final UserMapper mapper;

  @Inject
  public UserGateway(
    final UserEntityRepository repository, 
    final UserMapper mapper
  ) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public User save(final User user) {
    var entity = mapper.toEntity(user);
    repository.save(entity);
    return mapper.toDomain(entity);
  }

  @Override
  public Optional<User> findByEmail(final Email email) {
    return repository.findByEmail(email.value()).map(mapper::toDomain);
  }

  @Override
  public Optional<User> findById(final UserId id) {
    return repository
      .findById(id.value())
      .map(mapper::toDomain);
  }
  
}
