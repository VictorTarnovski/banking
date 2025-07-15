package com.victor_tarnovski.banking.infra.users;

import java.util.Optional;

import com.victor_tarnovski.banking.domain.aggregates.User;
import com.victor_tarnovski.banking.domain.ids.UserId;
import com.victor_tarnovski.banking.domain.repositories.UserRepository;
import com.victor_tarnovski.banking.domain.value_objects.Email;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class UserGateway implements UserRepository {
  private final UserEntityRepository repository;
  private final UserMapper mapper;

  public UserGateway(
    final UserEntityRepository repository, 
    final UserMapper mapper
  ) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public UserId newId() {
    return new UserId(repository.newId());
  }

  @Override
  public User create(final User user) {
    var entity = mapper.toEntity(user);
    repository.save(entity);
    return mapper.toDomain(entity);
  }

  @Override
  public Optional<User> findByEmail(final Email email) {
    return repository.findByEmail(email.value()).map(mapper::toDomain);
  }
  
}
