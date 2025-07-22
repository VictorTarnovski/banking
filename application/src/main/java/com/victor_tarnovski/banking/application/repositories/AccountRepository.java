package com.victor_tarnovski.banking.application.repositories;

import java.util.Optional;

import com.victor_tarnovski.banking.domain.aggregates.Account;
import com.victor_tarnovski.banking.domain.ids.AccountId;
import com.victor_tarnovski.banking.domain.ids.UserId;

public interface AccountRepository {
  Account save(final Account account);
  Optional<Account> findById(final AccountId id);
  Optional<Account> findByUserId(final UserId userId);
}
