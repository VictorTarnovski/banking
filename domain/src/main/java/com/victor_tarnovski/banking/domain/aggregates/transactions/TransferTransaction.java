package com.victor_tarnovski.banking.domain.aggregates.transactions;

import com.victor_tarnovski.banking.domain.aggregates.enums.TransactionType;
import com.victor_tarnovski.banking.domain.ids.TransactionId;
import com.victor_tarnovski.banking.domain.ids.WalletId;
import com.victor_tarnovski.banking.domain.vo.Money;

public class TransferTransaction extends Transaction {
  public TransferTransaction(
    TransactionId id,
    Money amount, 
    WalletId fromWalletId, 
    WalletId toWalletId
  ) {
    super(id, amount, fromWalletId, toWalletId, TransactionType.TRANSFER);
    requireNonNullFromWalletId(fromWalletId);
    requireNonNullToWalletId(toWalletId);
  }
  
  public TransferTransaction(
    Money amount, 
    WalletId fromWalletId, 
    WalletId toWalletId
  ) {
    this(null, amount, fromWalletId, toWalletId);
  }
  
  public WalletId fromWalletId() {
    return fromWalletId;
  }

  public WalletId toWalletId() {
    return toWalletId;
  }

}
