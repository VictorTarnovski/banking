package com.victor_tarnovski.banking.application.adapters;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.regex.Pattern;

import com.victor_tarnovski.banking.application.exceptions.UnauthorizedTransferException;
import com.victor_tarnovski.banking.domain.aggregates.Wallet;
import com.victor_tarnovski.banking.domain.ports.TransferAuthorizer;
import com.victor_tarnovski.banking.domain.vo.Money;

public class TransferAuthorizerAdapter implements TransferAuthorizer {
  @Override
  public void authorize(Wallet fromWallet, Wallet toWallet, Money amount) {
    // Simulating an external authorization service call
    // In a real application, this would be replaced with actual logic to check authorization
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://util.devi.tools/api/v2/authorize"))
        .build();

    try {
      HttpResponse<String> response= client.send(request, BodyHandlers.ofString());
      var pattern = Pattern.compile("\"authorization\" : true");
      var matcher = pattern.matcher(response.body());
      if (!matcher.find()) {
        throw new UnauthorizedTransferException();
      }
    } catch (IOException | InterruptedException e) {
      throw new UnauthorizedTransferException(e);
    }
  }
  
}
