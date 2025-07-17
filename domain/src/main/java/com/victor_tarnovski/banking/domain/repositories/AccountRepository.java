package com.victor_tarnovski.banking.domain.repositories;

import com.victor_tarnovski.banking.domain.aggregates.Account;

public interface AccountRepository {
  Account save(Account account);
}
