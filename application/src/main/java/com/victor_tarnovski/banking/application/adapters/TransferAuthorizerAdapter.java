package com.victor_tarnovski.banking.application.adapters;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.victor_tarnovski.banking.application.exceptions.UnauthorizedTransferException;
import com.victor_tarnovski.banking.domain.aggregates.Wallet;
import com.victor_tarnovski.banking.domain.ports.TransferAuthorizer;
import com.victor_tarnovski.banking.domain.vo.Money;

public class TransferAuthorizerAdapter implements TransferAuthorizer {

  private static final Logger log = LoggerFactory.getLogger(TransferAuthorizerAdapter.class);
  
  @Override
  public void authorize(Wallet fromWallet, Wallet toWallet, Money amount) {
    // Simulating an external authorization service call
    // In a real application, this would be replaced with actual logic to check authorization
    HttpClient client = HttpClient.newHttpClient();
    URI uri = URI.create("https://util.devi.tools/api/v2/authorize");
    HttpRequest request = HttpRequest.newBuilder()
        .GET()
        .uri(uri) 
        .build();

    log.info(request.toString());

    try {
      HttpResponse<String> response= client.send(request, BodyHandlers.ofString());
      log.info(response.toString());
      log.info(response.body());
      if(response.statusCode() != 200) {
        throw new UnauthorizedTransferException();
      }
    } catch (IOException | InterruptedException e) {
      throw new UnauthorizedTransferException(e);
    }
  }
  
}
