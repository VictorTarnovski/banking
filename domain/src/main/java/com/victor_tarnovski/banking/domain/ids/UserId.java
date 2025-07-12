package com.victor_tarnovski.banking.domain.ids;

import java.util.Objects;
import java.util.UUID;

public record UserId(UUID value) {
  public UserId {
    Objects.requireNonNull(value, "value must not be null");
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
