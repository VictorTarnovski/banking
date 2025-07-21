package com.victor_tarnovski.banking.application.repositories;

import java.util.Optional;

import com.victor_tarnovski.banking.domain.aggregates.Account;
import com.victor_tarnovski.banking.domain.ids.AccountId;

public interface AccountRepository {
  Account save(Account account);
  Optional<Account> findById(AccountId id);
}
