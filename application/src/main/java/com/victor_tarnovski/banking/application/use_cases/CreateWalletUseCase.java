package com.victor_tarnovski.banking.application.use_cases;

import java.util.Currency;
import java.util.Optional;

import com.victor_tarnovski.banking.application.exceptions.UserNotFoundException;
import com.victor_tarnovski.banking.application.repositories.WalletRepository;
import com.victor_tarnovski.banking.application.repositories.UserRepository;
import com.victor_tarnovski.banking.domain.aggregates.Wallet;
import com.victor_tarnovski.banking.domain.aggregates.User;
import com.victor_tarnovski.banking.domain.ids.UserId;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class CreateWalletUseCase {
  private final UserRepository userRepository;
  private final WalletRepository walletRepository;

  @Inject
  public CreateWalletUseCase(
    final UserRepository userRepository,
    final WalletRepository walletRepository
  ) {
    this.userRepository = userRepository;
    this.walletRepository = walletRepository;
  }

  public void execute(Currency currency, UserId userId) {
    User user = userRepository.findById(userId)
      .orElseThrow(() -> new UserNotFoundException(userId));

    Optional<Wallet> walletOpt = walletRepository.findByUserId(user.id()); 
    if(walletOpt.isPresent()) return;
    
    var wallet = new Wallet(currency, user.id());
    walletRepository.save(wallet);
  }
}
