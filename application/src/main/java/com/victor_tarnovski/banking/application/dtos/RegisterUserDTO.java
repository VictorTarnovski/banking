package com.victor_tarnovski.banking.application.dtos;

public record RegisterUserDTO(
  String fullName,
  String document,
  String email,
  String password) {
}
