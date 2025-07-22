package com.victor_tarnovski.banking.domain.services;

import com.victor_tarnovski.banking.domain.aggregates.Account;
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
    final Account debtorAccount, 
    final Account creditorAccount) {
    if (debtorAccount.id().equals(creditorAccount.id()))
      throw new RecursiveTransferException();

    var amountTransfered = new Money(transferAmount, debtorAccount.currency()); 
    debtorAccount.debit(amountTransfered);
    creditorAccount.credit(amountTransfered);

    return new Transaction(
      amountTransfered, 
      debtorAccount.id(), 
      creditorAccount.id()
    );
  }
}
