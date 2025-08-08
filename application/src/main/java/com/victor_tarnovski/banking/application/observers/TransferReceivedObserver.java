package com.victor_tarnovski.banking.application.observers;

import jakarta.enterprise.event.Observes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victor_tarnovski.banking.application.box_messages.BoxMessage;
import com.victor_tarnovski.banking.application.box_messages.OutBoxMessage;
import com.victor_tarnovski.banking.application.repositories.OutBoxMessageRepository;
import com.victor_tarnovski.banking.domain.events.TransferReceivedEvent;

public class TransferReceivedObserver {
    private final OutBoxMessageRepository repository;
    private final ObjectMapper mapper;

    public TransferReceivedObserver(
        final OutBoxMessageRepository repository,
        final ObjectMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void onTransferReceived(@Observes TransferReceivedEvent event) {
        var inbox = BoxMessage.of(event, mapper).map(OutBoxMessage::new);
        repository.create(inbox);        
    }
}