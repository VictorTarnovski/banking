package com.victor_tarnovski.banking.application.use_cases;

import com.victor_tarnovski.banking.application.repositories.UserRepository;
import com.victor_tarnovski.banking.domain.aggregates.User;
import com.victor_tarnovski.banking.application.dtos.RegisterUserDTO;
import com.victor_tarnovski.banking.domain.exceptions.EmailAlreadyInUseException;
import com.victor_tarnovski.banking.domain.vo.Email;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

@Named
@ApplicationScoped
public class RegisterUserUseCase {
  private final UserRepository repository;

  @Inject
  public RegisterUserUseCase(UserRepository repository) {
    this.repository = repository;
  }
 
  @Transactional
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

    repository.save(user);
  }  
}
