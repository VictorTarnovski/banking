package com.victor_tarnovski.banking.infra.users;

import java.lang.reflect.Field;

import com.victor_tarnovski.banking.domain.aggregates.User;
import com.victor_tarnovski.banking.domain.ids.UserId;
import com.victor_tarnovski.banking.domain.value_objects.Password;
import com.victor_tarnovski.banking.infra.exceptions.InfraException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class UserMapper {
  private final String PASSWORD_PLACEHOLDER = "********";

  public UserEntity toEntity(final User user) {
    var passwordSalt = getPasswordSalt(user);
    var passwordValue = getPasswordValue(user);

    var id = user.id() != null ? user.id().value() : null;
    return UserEntity.builder()
        .id(id)
        .fullName(user.fullName())
        .document(user.document())
        .email(user.email())
        .passwordSalt(passwordSalt)
        .passwordValue(passwordValue)
        .build();
  }

  public User toDomain(final UserEntity entity) {
    var user = new User(
      new UserId(entity.id), 
      entity.fullName, 
      entity.document, 
      entity.email,
      PASSWORD_PLACEHOLDER
    );

    setPassword(entity, user);

    return user;
  }

  private String getPasswordSalt(final User user) {
    try {
      Field passwordField = User.class.getDeclaredField("password");

      passwordField.setAccessible(true);

      var password = (Password) passwordField.get(user);

      Field saltField = Password.class.getDeclaredField("salt");
      saltField.setAccessible(true);
      var salt = saltField.get(password); 
      saltField.setAccessible(false);
      
      return (String) salt; 

    } catch (NoSuchFieldException | SecurityException | IllegalAccessException e) {
      e.printStackTrace();
      throw new InfraException("there was a error getting the password salt for User#" + user.id());
    }
  }

  private String getPasswordValue(final User user) {
    try {
      Field passwordField = User.class.getDeclaredField("password");

      passwordField.setAccessible(true);

      var password = (Password) passwordField.get(user);

      Field valueField = Password.class.getDeclaredField("value");
      valueField.setAccessible(true);
      var value = valueField.get(password);
      valueField.setAccessible(false);

      return (String) value;

    } catch (NoSuchFieldException | SecurityException | IllegalAccessException e) {
      e.printStackTrace();
      throw new InfraException("there was a error getting the password value for User#" + user.id());
    }
  }

  private void setPassword(final UserEntity entity, final User user) {
    try {
      Field passwordField = User.class.getDeclaredField("password");

      passwordField.setAccessible(true);

      var password = (Password) passwordField.get(user);

      Field saltField = Password.class.getDeclaredField("salt");
      saltField.setAccessible(true);
      saltField.set(password, entity.passwordSalt);
      saltField.setAccessible(false);

      Field valueField = Password.class.getDeclaredField("value");
      valueField.setAccessible(true);
      valueField.set(password, entity.passwordValue);
      valueField.setAccessible(false);

    } catch (NoSuchFieldException | SecurityException | IllegalAccessException e) {
      e.printStackTrace();
      throw new InfraException("there was a error setting the password for User#" + user.id());
    }
  }
}