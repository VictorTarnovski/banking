package com.victor_tarnovski.banking.presentation.controllers;

import java.util.Currency;
import java.util.UUID;

import com.victor_tarnovski.banking.application.use_cases.AddBalanceUseCase;
import com.victor_tarnovski.banking.application.use_cases.OpenAccountUseCase;
import com.victor_tarnovski.banking.domain.ids.AccountId;
import com.victor_tarnovski.banking.domain.ids.UserId;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/v1/accounts")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class AccountController {
  private final OpenAccountUseCase openAccountUseCase;
  private final AddBalanceUseCase addBalanceUseCase;

  @Inject
  AccountController(
    final OpenAccountUseCase openAccountUseCase,
    final AddBalanceUseCase addBalanceUseCase
  ) {
    this.openAccountUseCase = openAccountUseCase;
    this.addBalanceUseCase = addBalanceUseCase;
  }

  @POST
  public Response open(
    @HeaderParam("X-UserId")
    final UUID userId
  ) {
    openAccountUseCase.execute(Currency.getInstance("USD"), new UserId(userId));
    return Response.ok().build();
  }

  @POST
  @Path("/{accountId}/balance/{amount}")
  public Response addBalance(
    @PathParam("accountId")
    final UUID accountId,
    @PathParam("amount")
    final long amount 
  ) {
    addBalanceUseCase.execute(new AccountId(accountId), amount);
    return Response.ok().build();
  } 

}