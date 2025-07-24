package com.victor_tarnovski.banking.presentation.exception_mappers;

import com.victor_tarnovski.banking.domain.exceptions.RecursiveTransferException;
import com.victor_tarnovski.banking.presentation.Problem;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class RecursiveTransferExceptionMapper implements ExceptionMapper<RecursiveTransferException> {
  @Override
  public Response toResponse(RecursiveTransferException exception) {
    var problem = new Problem(
      exception.getMessage(),
      Response.Status.BAD_REQUEST.getStatusCode(),
      "paying wallet cannot be the same as the receiving wallet"
    );

    return Response.status(problem.status())
      .entity(problem)
      .type(MediaType.APPLICATION_JSON)
      .build();
  }
  
}
