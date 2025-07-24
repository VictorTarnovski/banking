package com.victor_tarnovski.banking.presentation.controllers;

import java.util.Currency;
import java.util.UUID;

import com.victor_tarnovski.banking.application.use_cases.WithdrawUseCase;
import com.victor_tarnovski.banking.application.use_cases.CreateWalletUseCase;
import com.victor_tarnovski.banking.application.use_cases.DepositUseCase;
import com.victor_tarnovski.banking.application.use_cases.TransferFundsUseCase;
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
  private final DepositUseCase depositUseCase;
  private final WithdrawUseCase withdrawUseCase;
  private final TransferFundsUseCase transferFundsUseCase; 

  @Inject
  WalletController(
    final CreateWalletUseCase createWalletUseCase,
    final DepositUseCase depositUseCase,
    final WithdrawUseCase withdrawUseCase,
    final TransferFundsUseCase transferFundsUseCase 
  ) {
    this.createWalletUseCase = createWalletUseCase;
    this.depositUseCase = depositUseCase;
    this.withdrawUseCase = withdrawUseCase;
    this.transferFundsUseCase = transferFundsUseCase;
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
  @Path("/{walletId}/deposit/{amount}")
  public Response deposit(
    @PathParam("walletId")
    final UUID walletId,
    @PathParam("amount")
    final long amount 
  ) {
    depositUseCase.execute(new WalletId(walletId), amount);
    return Response.ok().build();
  } 

  @POST
  @Path("/{walletId}/withdraw/{amount}")
  public Response withdraw(
    @PathParam("walletId")
    final UUID walletId,
    @PathParam("amount")
    final long amount 
  ) {
    withdrawUseCase.execute(new WalletId(walletId), amount);
    return Response.ok().build();
  } 

  @POST
  @Path("/{fromWalletId}/transfer/{toWalletId}/{amount}")
  public Response transfer(
    @PathParam("fromWalletId")
    final UUID fromWalletId,
    @PathParam("toWalletId")
    final UUID toWalletId,
    @PathParam("amount")
    final long amount 
  ) {
    transferFundsUseCase.execute(amount, new WalletId(fromWalletId), new WalletId(toWalletId));
    return Response.ok().build();
  } 
}