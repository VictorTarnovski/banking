package com.victor_tarnovski.banking.application.use_cases;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.victor_tarnovski.banking.domain.events.TransferReceivedEvent;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotifyTransferReceivedEventUseCase {
    private static final Logger log = LoggerFactory.getLogger(NotifyTransferReceivedEventUseCase.class);

    public void execute(TransferReceivedEvent event) {
        // Simulating an external notification service call
        // In a real application, this would be replaced with some email messaging or sms
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create("https://util.devi.tools/api/v1/notify");
        HttpRequest request = HttpRequest.newBuilder()
                .POST(BodyPublishers.noBody())
                .uri(uri)
                .build();

        log.info(request.toString());

        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            log.info(response.toString());
            log.info(response.body());
            if (response.statusCode() != 204) {
                throw new RuntimeException("could not notify");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("could not notify", e);
        }
    }
}
