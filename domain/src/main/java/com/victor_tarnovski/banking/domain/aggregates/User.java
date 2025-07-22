package com.victor_tarnovski.banking.domain.aggregates;

import com.victor_tarnovski.banking.domain.exceptions.PasswordMismatchException;
import com.victor_tarnovski.banking.domain.ids.UserId;
import com.victor_tarnovski.banking.domain.vo.Email;
import com.victor_tarnovski.banking.domain.vo.Password;

import java.util.Objects;

public class User {
  private final UserId id;
  private final String fullName;
  private final String document;
  private final Email email;
  private Password password;

  public User(
    final String fullName,
    final String document,
    final String email,
    final String password
  ) {
    this(null, fullName, document, email, password);
  }
  
  public User(
    final UserId id,
    final String fullName,
    final String document,
    final String email,
    final String password
  ) {
    this.id = id;

    Objects.requireNonNull(fullName, "fullName must not be null");
    this.fullName = fullName;

    Objects.requireNonNull(email, "email must not be null");
    this.email = new Email(email);

    Objects.requireNonNull(password, "password must not be null");
    changePassword(password); 

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

  public void changePassword(String oldPassword, String newPassword) {
    if(!password.equals(oldPassword))
      throw new PasswordMismatchException(); 
    
    changePassword(newPassword);
  }

  private void changePassword(String input) {
    password = new Password(input);
  }
}
