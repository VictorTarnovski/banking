package com.victor_tarnovski.banking.application.use_cases;

import com.victor_tarnovski.banking.application.exceptions.AccountNotFoundException;
import com.victor_tarnovski.banking.application.repositories.AccountRepository;
import com.victor_tarnovski.banking.domain.ids.AccountId;
import com.victor_tarnovski.banking.domain.vo.Money;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class AddBalanceUseCase {
  private final AccountRepository accountRepository;
  
  public AddBalanceUseCase(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public void execute(final AccountId accountId, final long amount) {
    var account = accountRepository.findById(accountId)
      .orElseThrow(() -> new AccountNotFoundException(accountId));

    account.credit(new Money(amount, account.currency()));
  
    accountRepository.save(account);
  }
}
