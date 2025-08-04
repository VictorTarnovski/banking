package com.victor_tarnovski.banking.domain.services;

import com.victor_tarnovski.banking.domain.aggregates.Wallet;
import com.victor_tarnovski.banking.domain.aggregates.transactions.TransferTransaction;
import com.victor_tarnovski.banking.domain.exceptions.RecursiveTransferException;
import com.victor_tarnovski.banking.domain.ports.TransferAuthorizer;
import com.victor_tarnovski.banking.domain.vo.Money;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class TransferService {
  private final TransferAuthorizer authorizer;

  @Inject
  public TransferService(final TransferAuthorizer authorizer) {
    this.authorizer = authorizer;
  }

  public TransferTransaction transfer(
    final long transferAmount, 
    final Wallet fromWallet, 
    final Wallet toWallet
  ) {
    if (fromWallet.equals(toWallet))
      throw new RecursiveTransferException();

    var amountTransfered = new Money(transferAmount, fromWallet.currency()); 

    authorizer.authorize(fromWallet, toWallet, amountTransfered);

    fromWallet.withdraw(amountTransfered);
    toWallet.deposit(amountTransfered);

    return new TransferTransaction(
      amountTransfered, 
      fromWallet.id(), 
      toWallet.id()
    );
  }
}
