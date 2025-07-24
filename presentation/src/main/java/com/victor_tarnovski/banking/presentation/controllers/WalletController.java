package com.victor_tarnovski.banking.presentation.controllers;

import java.util.Currency;
import java.util.UUID;

import com.victor_tarnovski.banking.application.use_cases.AddBalanceUseCase;
import com.victor_tarnovski.banking.application.use_cases.CreateWalletUseCase;
import com.victor_tarnovski.banking.domain.ids.WalletId;
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

@Path("/v1/wallets")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class WalletController {
  private final CreateWalletUseCase createWalletUseCase;
  private final AddBalanceUseCase addBalanceUseCase;

  @Inject
  WalletController(
    final CreateWalletUseCase createWalletUseCase,
    final AddBalanceUseCase addBalanceUseCase
  ) {
    this.createWalletUseCase = createWalletUseCase;
    this.addBalanceUseCase = addBalanceUseCase;
  }

  @POST
  public Response create(
    @HeaderParam("X-UserId")
    final UUID userId
  ) {
    createWalletUseCase.execute(Currency.getInstance("USD"), new UserId(userId));
    return Response.ok().build();
  }

  @POST
  @Path("/{walletId}/balance/{amount}")
  public Response addBalance(
    @PathParam("walletId")
    final UUID walletId,
    @PathParam("amount")
    final long amount 
  ) {
    addBalanceUseCase.execute(new WalletId(walletId), amount);
    return Response.ok().build();
  } 

}