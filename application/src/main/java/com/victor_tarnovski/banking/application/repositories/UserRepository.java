package com.victor_tarnovski.banking.application.repositories;

import java.util.Optional;

import com.victor_tarnovski.banking.domain.aggregates.User;
import com.victor_tarnovski.banking.domain.ids.UserId;
import com.victor_tarnovski.banking.domain.value_objects.Email;

public interface UserRepository {
  User save(User user);
  Optional<User> findByEmail(Email email);
  Optional<User> findById(UserId id);
}
