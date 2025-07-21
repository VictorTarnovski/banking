package com.victor_tarnovski.banking.presentation.controllers;

import java.util.UUID;

import com.victor_tarnovski.banking.application.use_cases.OpenAccountUseCase;
import com.victor_tarnovski.banking.domain.ids.UserId;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/v1/accounts")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class AccountController {
  private final OpenAccountUseCase useCase;

  @Inject
  AccountController(final OpenAccountUseCase useCase) {
    this.useCase = useCase;
  }

  @POST
  public Response open(
    @HeaderParam("X-UserId")
    final UUID userId
  ) {
    useCase.execute(new UserId(userId));
    return Response.ok().build();
  }

}