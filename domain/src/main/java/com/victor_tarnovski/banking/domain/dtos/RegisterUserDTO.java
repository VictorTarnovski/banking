package com.victor_tarnovski.banking.domain.dtos;

public record RegisterUserDTO(
  String fullName,
  String document,
  String email,
  String password) {
}
