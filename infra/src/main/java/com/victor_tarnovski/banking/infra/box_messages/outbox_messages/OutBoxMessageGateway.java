package com.victor_tarnovski.banking.infra.box_messages.outbox_messages;

import java.util.List;

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

    @Override
    public List<OutBoxMessage> findUnprocessedByType(String type) {
        return this.findUnprocessedByType(type, 30);
    }

    private List<OutBoxMessage> findUnprocessedByType(String type, int limit) {
        return repository.findUnprocessedByType(type, limit)
            .stream()
            .map(mapper::toApplication)
            .toList();
    }
    
    @Override
    public void update(OutBoxMessage message) {
        var entity = mapper.toEntity(message);
        repository.update(entity);
    }
}
