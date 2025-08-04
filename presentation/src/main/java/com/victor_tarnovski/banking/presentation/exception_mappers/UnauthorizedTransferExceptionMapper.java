package com.victor_tarnovski.banking.presentation.exception_mappers;

import com.victor_tarnovski.banking.domain.exceptions.UnauthorizedTransferException;
import com.victor_tarnovski.banking.presentation.Problem;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class UnauthorizedTransferExceptionMapper implements ExceptionMapper<UnauthorizedTransferException> {

  @Override
  public Response toResponse(UnauthorizedTransferException exception) {
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
