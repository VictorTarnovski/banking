package com.victor_tarnovski.banking.presentation;

import com.victor_tarnovski.banking.domain.exceptions.EmailAlreadyInUseException;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandlerImpl implements GlobalExceptionHandler {
  @Override
  public Response toResponse(EmailAlreadyInUseException exception) {
    var problem = new Problem(
      exception.getMessage(),
      Response.Status.CONFLICT.getStatusCode()
    );

    return Response.status(problem.status())
      .entity(problem)
      .type(MediaType.APPLICATION_JSON)
      .build();
  }
  
}