package com.victor_tarnovski.banking.application.use_cases;

import com.victor_tarnovski.banking.domain.aggregates.User;
import com.victor_tarnovski.banking.domain.dtos.RegisterUserDTO;
import com.victor_tarnovski.banking.domain.exceptions.EmailAlreadyInUseException;
import com.victor_tarnovski.banking.domain.repositories.UserRepository;
import com.victor_tarnovski.banking.domain.value_objects.Email;

public class RegisterUserUseCase {
  private final UserRepository repository;

  public RegisterUserUseCase(UserRepository repository) {
    this.repository = repository;
  }
  
  public void execute(RegisterUserDTO dto) {
    var existing = repository.findByEmail(new Email(dto.email()));
    if(existing.isPresent())
      throw new EmailAlreadyInUseException();

    var user = new User(
      dto.fullName(),
      dto.document(), 
      dto.email(),
      dto.password()
    );

    repository.create(user);
  }  
}
