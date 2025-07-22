package com.victor_tarnovski.banking.application.use_cases;

import java.util.Currency;
import java.util.Optional;

import com.victor_tarnovski.banking.application.exceptions.UserNotFoundException;
import com.victor_tarnovski.banking.application.repositories.AccountRepository;
import com.victor_tarnovski.banking.application.repositories.UserRepository;
import com.victor_tarnovski.banking.domain.aggregates.Account;
import com.victor_tarnovski.banking.domain.aggregates.User;
import com.victor_tarnovski.banking.domain.ids.UserId;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class OpenAccountUseCase {
  private final UserRepository userRepository;
  private final AccountRepository accountRepository;

  @Inject
  public OpenAccountUseCase(
    final UserRepository userRepository,
    final AccountRepository accountRepository
  ) {
    this.userRepository = userRepository;
    this.accountRepository = accountRepository;
  }

  public void execute(Currency currency, UserId userId) {
    User user = userRepository.findById(userId)
      .orElseThrow(() -> new UserNotFoundException(userId));

    Optional<Account> accountOpt = accountRepository.findByUserId(user.id()); 
    if(accountOpt.isPresent()) return;
    
    var account = new Account(currency, user.id());
  
    accountRepository.save(account);
  }
}
