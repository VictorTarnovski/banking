package com.victor_tarnovski.banking.domain.services;

import com.victor_tarnovski.banking.domain.aggregates.Wallet;
import com.victor_tarnovski.banking.domain.aggregates.transactions.TransferTransaction;
import com.victor_tarnovski.banking.domain.exceptions.RecursiveTransferException;
import com.victor_tarnovski.banking.domain.vo.Money;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class TransferService {
  public TransferTransaction transfer(
    final long transferAmount, 
    final Wallet fromWallet, 
    final Wallet toWallet) {
    if (fromWallet.equals(toWallet))
      throw new RecursiveTransferException();

    var amountTransfered = new Money(transferAmount, fromWallet.currency()); 
    fromWallet.withdraw(amountTransfered);
    toWallet.deposit(amountTransfered);

    return new TransferTransaction(
      amountTransfered, 
      fromWallet.id(), 
      toWallet.id()
    );
  }
}
