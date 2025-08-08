package com.victor_tarnovski.banking.quarkus.outbox_messages_processors;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.victor_tarnovski.banking.application.box_messages.OutBoxMessage;
import com.victor_tarnovski.banking.application.repositories.OutBoxMessageRepository;
import com.victor_tarnovski.banking.application.use_cases.NotifyTransferReceivedEventUseCase;
import com.victor_tarnovski.banking.domain.events.TransferReceivedEvent;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TransferReceivedOutBoxMessageProcessor {
    private final OutBoxMessageRepository repository;
    private final ObjectMapper mapper;
    private final NotifyTransferReceivedEventUseCase notifier;

    public TransferReceivedOutBoxMessageProcessor(
        final OutBoxMessageRepository repository,
        final ObjectMapper mapper,
        final NotifyTransferReceivedEventUseCase notifier
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.notifier = notifier;
    }

    @Transactional
    @Scheduled(every = "30s")
    void execute() throws JsonMappingException, JsonProcessingException {
        List<OutBoxMessage> messages = repository.findUnprocessedByType(TransferReceivedEvent.class.getTypeName());
        for (var message : messages) {
           var event = mapper.readValue(message.payload(), TransferReceivedEvent.class);
           notifier.execute(event);
           message.process();
           repository.update(message);
        }
    } 
}
