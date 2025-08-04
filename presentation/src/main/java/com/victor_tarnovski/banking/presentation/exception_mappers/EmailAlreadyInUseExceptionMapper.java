package com.victor_tarnovski.banking.presentation.exception_mappers;

import com.victor_tarnovski.banking.application.exceptions.EmailAlreadyInUseException;
import com.victor_tarnovski.banking.presentation.Problem;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EmailAlreadyInUseExceptionMapper implements ExceptionMapper<EmailAlreadyInUseException> {
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