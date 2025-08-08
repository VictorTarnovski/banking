package com.victor_tarnovski.banking.infra.box_messages.outbox_messages;

import com.victor_tarnovski.banking.application.box_messages.BoxMessageId;
import com.victor_tarnovski.banking.application.box_messages.OutBoxMessage;
import com.victor_tarnovski.banking.infra.box_messages.BoxMessageEntity;
import com.victor_tarnovski.banking.infra.box_messages.BoxMessageMapper;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OutBoxMessageMapper {
    public OutBoxMessageEntity toEntity(OutBoxMessage message) {
        BoxMessageEntity entity = BoxMessageMapper.toEntity(message);    
        return new OutBoxMessageEntity(entity);
    }

    public OutBoxMessage toApplication(OutBoxMessageEntity entity) {
        return new OutBoxMessage(
            new BoxMessageId(entity.id),
            entity.type, 
            entity.payload
        );
    }
}
