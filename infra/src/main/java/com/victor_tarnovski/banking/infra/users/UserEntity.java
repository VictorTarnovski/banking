package com.victor_tarnovski.banking.infra.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

import java.util.UUID;

@Entity
@Table(name = "users")
@Builder
public class UserEntity {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public UUID id;
  @Column(name = "full_name")
  public String fullName;
  @Column(name = "document")
  public String document;
  @Column(name = "email")
  public String email;
  @Column(name = "password_salt")
  public String passwordSalt;
  @Column(name = "password_value")
  public String passwordValue;
}