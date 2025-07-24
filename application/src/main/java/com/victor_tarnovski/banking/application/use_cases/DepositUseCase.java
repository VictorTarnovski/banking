package com.victor_tarnovski.banking.application.use_cases;

import com.victor_tarnovski.banking.application.exceptions.WalletNotFoundException;
import com.victor_tarnovski.banking.application.repositories.TransactionRepository;
import com.victor_tarnovski.banking.application.repositories.WalletRepository;
import com.victor_tarnovski.banking.domain.aggregates.transactions.DepositTransaction;
import com.victor_tarnovski.banking.domain.ids.WalletId;
import com.victor_tarnovski.banking.domain.vo.Money;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

@Named
@ApplicationScoped
public class DepositUseCase {
  private final WalletRepository walletRepository;
  private final TransactionRepository transactionRepository;

  @Inject
  public DepositUseCase(
    final WalletRepository walletRepository,
    final TransactionRepository transactionRepository
  ) {
    this.walletRepository = walletRepository;
    this.transactionRepository = transactionRepository;
  }

  @Transactional
  public void execute(final WalletId walletId, final long amount) {
    var wallet = walletRepository.findById(walletId)
      .orElseThrow(() -> new WalletNotFoundException(walletId));

    var deposit = new Money(amount, wallet.currency());
    wallet.deposit(deposit);

    var transaction = new DepositTransaction(deposit, wallet.id());

    walletRepository.save(wallet);
    transactionRepository.save(transaction);
  }
}
