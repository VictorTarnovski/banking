package com.victor_tarnovski.banking.infra.box_messages.outbox_messages;

import com.victor_tarnovski.banking.application.box_messages.OutBoxMessage;
import com.victor_tarnovski.banking.application.repositories.OutBoxMessageRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OutBoxMessageGateway implements OutBoxMessageRepository {
    private final OutBoxMessageEntityRepository repository;
    private final OutBoxMessageMapper mapper;

    public OutBoxMessageGateway(
        OutBoxMessageEntityRepository repository,
        OutBoxMessageMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void create(OutBoxMessage message) {
        var entity = mapper.toEntity(message);
        repository.create(entity);
    }
}
