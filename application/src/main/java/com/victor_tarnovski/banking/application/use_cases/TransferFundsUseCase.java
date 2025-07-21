package com.victor_tarnovski.banking.application.use_cases;

import com.victor_tarnovski.banking.application.exceptions.AccountNotFoundException;
import com.victor_tarnovski.banking.application.repositories.AccountRepository;
import com.victor_tarnovski.banking.application.repositories.TransactionRepository;
import com.victor_tarnovski.banking.domain.ids.AccountId;
import com.victor_tarnovski.banking.domain.services.TransferService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class TransferFundsUseCase {
  private final AccountRepository accountRepository;
  private final TransferService transferService;
  private final TransactionRepository transactionRepository;

  public TransferFundsUseCase(
    final AccountRepository accountRepository,
    final TransferService transferService,
    final TransactionRepository transactionRepository
  ) {
    this.accountRepository = accountRepository;
    this.transferService = transferService;
    this.transactionRepository = transactionRepository;
  }

  public void execute(long transferAmount, AccountId debtorAccountId, AccountId creditorAccountId) {
    var debtorAccount = accountRepository
      .findById(debtorAccountId)
      .orElseThrow(() -> new AccountNotFoundException(debtorAccountId)); 

    var creditorAccount = accountRepository
      .findById(creditorAccountId)
      .orElseThrow(() -> new AccountNotFoundException(creditorAccountId)); 
   
    var transaction = transferService.transfer(
      transferAmount,
      debtorAccount,
      creditorAccount
    );
 
    accountRepository.save(debtorAccount);
    accountRepository.save(creditorAccount);
    transactionRepository.save(transaction);
  }
}
