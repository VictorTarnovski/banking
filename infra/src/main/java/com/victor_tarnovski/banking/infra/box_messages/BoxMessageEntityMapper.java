package com.victor_tarnovski.banking.infra.box_messages;

import com.victor_tarnovski.banking.application.BoxMessage;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BoxMessageEntityMapper {
    public static BoxMessageEntity toEntity(BoxMessage message) {
        return BoxMessageEntity.builder()
            .type(message.type())
            .payload(message.payload())
            .build();
    }
}
