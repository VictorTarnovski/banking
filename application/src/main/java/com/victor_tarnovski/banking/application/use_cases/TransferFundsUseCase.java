package com.victor_tarnovski.banking.application.use_cases;

import com.victor_tarnovski.banking.application.exceptions.WalletNotFoundException;
import com.victor_tarnovski.banking.application.repositories.WalletRepository;
import com.victor_tarnovski.banking.application.repositories.TransactionRepository;
import com.victor_tarnovski.banking.domain.ids.WalletId;
import com.victor_tarnovski.banking.domain.services.TransferService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class TransferFundsUseCase {
  private final WalletRepository walletRepository;
  private final TransferService transferService;
  private final TransactionRepository transactionRepository;

  public TransferFundsUseCase(
    final WalletRepository walletRepository,
    final TransferService transferService,
    final TransactionRepository transactionRepository
  ) {
    this.walletRepository = walletRepository;
    this.transferService = transferService;
    this.transactionRepository = transactionRepository;
  }

  public void execute(long transferAmount, WalletId fromWalletId, WalletId toWalletId) {
    var fromWallet = walletRepository
      .findById(fromWalletId)
      .orElseThrow(() -> new WalletNotFoundException(fromWalletId)); 

    var toWallet = walletRepository
      .findById(toWalletId)
      .orElseThrow(() -> new WalletNotFoundException(toWalletId)); 
   
    var transaction = transferService.transfer(
      transferAmount,
      fromWallet,
      toWallet
    );
 
    walletRepository.save(fromWallet);
    walletRepository.save(toWallet);
    transactionRepository.save(transaction);
  }
}
