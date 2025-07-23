package com.victor_tarnovski.banking.domain.ids;

import java.util.Objects;
import java.util.UUID;

public record WalletId(UUID value) {
  public WalletId {
    Objects.requireNonNull(value, "value must not be null");
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
