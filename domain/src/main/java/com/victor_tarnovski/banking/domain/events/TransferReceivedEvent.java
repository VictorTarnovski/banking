package com.victor_tarnovski.banking.domain.events;

import com.victor_tarnovski.banking.domain.ids.WalletId;
import com.victor_tarnovski.banking.domain.vo.Money;

public record TransferReceivedEvent(Money amount, WalletId walletId) {}