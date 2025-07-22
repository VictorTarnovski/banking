package com.victor_tarnovski.banking.domain.vo;

import java.util.Objects;
import java.util.regex.Pattern;

import com.victor_tarnovski.banking.domain.exceptions.InvalidEmailFormatException;

public record Email(String value) {

  private static final Pattern EMAIL_PATTERN = Pattern.compile(
      "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
      Pattern.CASE_INSENSITIVE);

  public Email {
    Objects.requireNonNull(value, "value must not be null");
    if (!EMAIL_PATTERN.matcher(value).matches()) {
      throw new InvalidEmailFormatException();
    }
  }
}
