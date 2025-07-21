package com.victor_tarnovski.banking.presentation;

import com.victor_tarnovski.banking.domain.exceptions.EmailAlreadyInUseException;

import jakarta.ws.rs.ext.ExceptionMapper;

public interface GlobalExceptionHandler extends 
  ExceptionMapper<EmailAlreadyInUseException> {}