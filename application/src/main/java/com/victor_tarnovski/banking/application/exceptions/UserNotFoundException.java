package com.victor_tarnovski.banking.application.exceptions;

import com.victor_tarnovski.banking.domain.ids.UserId;

public class UserNotFoundException extends NotFoundException {
  public UserNotFoundException(UserId userId) {
    super("User", userId.toString());
  }
}