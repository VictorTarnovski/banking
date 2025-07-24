package com.victor_tarnovski.banking.presentation.exception_mappers;

import com.victor_tarnovski.banking.domain.exceptions.InsufficientBalanceException;
import com.victor_tarnovski.banking.presentation.Problem;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InsufficientBalanceExceptionMapper implements ExceptionMapper<InsufficientBalanceException> {
  @Override
  public Response toResponse(InsufficientBalanceException exception) {
    var problem = new Problem(
      exception.getMessage(),
      Response.Status.BAD_REQUEST.getStatusCode()
    );

    return Response.status(problem.status())
      .entity(problem)
      .type(MediaType.APPLICATION_JSON)
      .build();
  }
  
}
