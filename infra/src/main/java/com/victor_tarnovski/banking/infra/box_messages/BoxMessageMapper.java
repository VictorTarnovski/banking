package com.victor_tarnovski.banking.infra.box_messages;

import com.victor_tarnovski.banking.application.box_messages.BoxMessage;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BoxMessageMapper {
    public static BoxMessageEntity toEntity(BoxMessage message) {
        var id = message.id() != null
                ? message.id().value()
                : null;

        return BoxMessageEntity.builder()
                .id(id)
                .type(message.type())
                .payload(message.payload())
                .processedAt(message.processedAt().get())
                .build();
    }
}
