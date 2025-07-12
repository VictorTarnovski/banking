package com.victor_tarnovski.banking.domain.repositories;

import java.util.Optional;

import com.victor_tarnovski.banking.domain.aggregates.User;
import com.victor_tarnovski.banking.domain.ids.UserId;
import com.victor_tarnovski.banking.domain.value_objects.Email;

public interface UserRepository {
  UserId nextId();
  void create(User user);
  Optional<User>findByEmail(Email email);
}
