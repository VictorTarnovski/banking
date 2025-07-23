package com.victor_tarnovski.banking.application.repositories;

import java.util.Optional;

import com.victor_tarnovski.banking.domain.aggregates.Wallet;
import com.victor_tarnovski.banking.domain.ids.WalletId;
import com.victor_tarnovski.banking.domain.ids.UserId;

public interface WalletRepository {
  Wallet save(final Wallet wallet);
  Optional<Wallet> findById(final WalletId id);
  Optional<Wallet> findByUserId(final UserId userId);
}
