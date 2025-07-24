package com.victor_tarnovski.banking.infra.wallets;

import java.util.Optional;

import com.victor_tarnovski.banking.application.repositories.WalletRepository;
import com.victor_tarnovski.banking.domain.aggregates.Wallet;
import com.victor_tarnovski.banking.domain.ids.WalletId;
import com.victor_tarnovski.banking.domain.ids.UserId;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class WalletGateway implements WalletRepository {
  private final WalletEntityRepository repository;
  private final WalletMapper mapper;

  public WalletGateway(
    final WalletEntityRepository repository, 
    final WalletMapper mapper
  ) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public Wallet save(final Wallet wallet) {
    var entity = mapper.toEntity(wallet);

    if(entity.id == null) {
      repository.create(entity);
      return mapper.toDomain(entity);
    }

    repository.update(entity);
    return mapper.toDomain(entity);
  }

  @Override
  public Optional<Wallet> findById(final WalletId id) {
    return repository
      .findById(id.value())
      .map(mapper::toDomain);
  }

  @Override
  public Optional<Wallet> findByUserId(final UserId userId) {
    return repository
      .findByUserId(userId.value())
      .map(mapper::toDomain);
  }
  
}
