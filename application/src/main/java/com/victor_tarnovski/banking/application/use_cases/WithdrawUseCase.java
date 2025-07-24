package com.victor_tarnovski.banking.application.use_cases;

import com.victor_tarnovski.banking.application.exceptions.WalletNotFoundException;
import com.victor_tarnovski.banking.application.repositories.TransactionRepository;
import com.victor_tarnovski.banking.application.repositories.WalletRepository;
import com.victor_tarnovski.banking.domain.aggregates.transactions.WithdrawTransaction;
import com.victor_tarnovski.banking.domain.ids.WalletId;
import com.victor_tarnovski.banking.domain.vo.Money;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

@Named
@ApplicationScoped
public class WithdrawUseCase {
  private final WalletRepository walletRepository;
  private final TransactionRepository transactionRepository;

  @Inject
  public WithdrawUseCase(
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

    var withdraw = new Money(amount, wallet.currency());
    wallet.withdraw(withdraw);

    var transaction = new WithdrawTransaction(withdraw, wallet.id());

    walletRepository.save(wallet);
    transactionRepository.create(transaction);
  }
}
