package com.victor_tarnovski.banking.infra.box_messages.outbox_messages;

import com.victor_tarnovski.banking.application.OutBoxMessage;
import com.victor_tarnovski.banking.infra.box_messages.BoxMessageEntity;
import com.victor_tarnovski.banking.infra.box_messages.BoxMessageEntityMapper;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OutBoxMessageMapper {
    public OutBoxMessageEntity toEntity(OutBoxMessage message) {
        BoxMessageEntity entity = BoxMessageEntityMapper.toEntity(message);    
        return new OutBoxMessageEntity(entity);
    }
}
