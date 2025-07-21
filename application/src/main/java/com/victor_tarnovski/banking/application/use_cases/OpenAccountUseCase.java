package com.victor_tarnovski.banking.application.use_cases;

import com.victor_tarnovski.banking.application.repositories.AccountRepository;
import com.victor_tarnovski.banking.domain.aggregates.Account;
import com.victor_tarnovski.banking.domain.ids.UserId;
import com.victor_tarnovski.banking.domain.value_objects.Money;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class OpenAccountUseCase {
  private final AccountRepository repository;

  @Inject
  public OpenAccountUseCase(final AccountRepository repository) {
    this.repository = repository;
  }

  public void execute(UserId userId) {
    var initialBalance = Money.dollars(); 
    var account = new Account(initialBalance, userId);
  
    repository.save(account);
  }
}
