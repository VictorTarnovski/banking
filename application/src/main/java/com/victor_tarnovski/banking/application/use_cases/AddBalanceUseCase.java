package com.victor_tarnovski.banking.application.use_cases;

import com.victor_tarnovski.banking.application.exceptions.WalletNotFoundException;
import com.victor_tarnovski.banking.application.repositories.WalletRepository;
import com.victor_tarnovski.banking.domain.ids.WalletId;
import com.victor_tarnovski.banking.domain.vo.Money;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class AddBalanceUseCase {
  private final WalletRepository walletRepository;
  
  public AddBalanceUseCase(WalletRepository walletRepository) {
    this.walletRepository = walletRepository;
  }

  public void execute(final WalletId walletId, final long amount) {
    var wallet = walletRepository.findById(walletId)
      .orElseThrow(() -> new WalletNotFoundException(walletId));

    wallet.deposit(new Money(amount, wallet.currency()));
  
    walletRepository.save(wallet);
  }
}
