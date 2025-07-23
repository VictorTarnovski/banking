package com.victor_tarnovski.banking.domain.services;

import com.victor_tarnovski.banking.domain.aggregates.Wallet;
import com.victor_tarnovski.banking.domain.aggregates.Transaction;
import com.victor_tarnovski.banking.domain.exceptions.RecursiveTransferException;
import com.victor_tarnovski.banking.domain.vo.Money;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class TransferService {
  public Transaction transfer(
    final long transferAmount, 
    final Wallet debtorWallet, 
    final Wallet creditorWallet) {
    if (debtorWallet.id().equals(creditorWallet.id()))
      throw new RecursiveTransferException();

    var amountTransfered = new Money(transferAmount, debtorWallet.currency()); 
    debtorWallet.debit(amountTransfered);
    creditorWallet.credit(amountTransfered);

    return new Transaction(
      amountTransfered, 
      debtorWallet.id(), 
      creditorWallet.id()
    );
  }
}
