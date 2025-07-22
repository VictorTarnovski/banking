package com.victor_tarnovski.banking.infra.accounts;

import java.util.Optional;

import com.victor_tarnovski.banking.application.repositories.AccountRepository;
import com.victor_tarnovski.banking.domain.aggregates.Account;
import com.victor_tarnovski.banking.domain.ids.AccountId;
import com.victor_tarnovski.banking.domain.ids.UserId;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class AccountGateway implements AccountRepository {
  private final AccountEntityRepository repository;
  private final AccountMapper mapper;

  public AccountGateway(
    final AccountEntityRepository repository, 
    final AccountMapper mapper
  ) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public Account save(final Account account) {
    var entity = mapper.toEntity(account);
    repository.save(entity);
    return mapper.toDomain(entity);
  }

  @Override
  public Optional<Account> findById(final AccountId id) {
    return repository
      .findById(id.value())
      .map(mapper::toDomain);
  }

  @Override
  public Optional<Account> findByUserId(final UserId userId) {
    return repository
      .findByUserId(userId.value())
      .map(mapper::toDomain);
  }
  
}
