package com.victor_tarnovski.banking.domain.aggregates;

import java.util.Objects;

import com.victor_tarnovski.banking.domain.ids.UserId;
import com.victor_tarnovski.banking.domain.value_objects.Email;
import com.victor_tarnovski.banking.domain.value_objects.Password;

public class User {
  private final UserId id;
  private final String fullName;
  private final String document;
  private final Email email;
  private final Password password;

  public User(
    final UserId id,
    final String fullName,
    final String document,
    final String email,
    final String password
  ) {
    Objects.requireNonNull(id, "id must not be null");
    this.id = id;

    Objects.requireNonNull(fullName, "fullName must not be null");
    this.fullName = fullName;

    Objects.requireNonNull(email, "email must not be null");
    this.email = new Email(email);

    Objects.requireNonNull(password, "password must not be null");
    this.password = new Password(password);

    Objects.requireNonNull(document, "document must not be null");
    this.document = document;
  }

  public UserId id() {
    return id;
  }

  public String fullName() {
    return fullName;
  }

  public String email() {
    return email.value();
  }

  public String document() {
    return document;
  }

  public boolean hasPassword(String input) {
    return password.equals(input);
  }
}
