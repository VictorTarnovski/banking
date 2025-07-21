package com.victor_tarnovski.banking.presentation.controllers;

import com.victor_tarnovski.banking.application.dtos.RegisterUserDTO;
import com.victor_tarnovski.banking.application.use_cases.RegisterUserUseCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/v1/auth")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class AuthController {
  private final RegisterUserUseCase useCase;

  @Inject
  AuthController(final RegisterUserUseCase useCase) {
    this.useCase = useCase;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response register(final RegisterUserDTO dto) {
    useCase.execute(dto);
    return Response.ok().build();
  }

}