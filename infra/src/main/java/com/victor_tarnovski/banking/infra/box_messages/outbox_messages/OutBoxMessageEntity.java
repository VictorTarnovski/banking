package com.victor_tarnovski.banking.infra.box_messages.outbox_messages;

import com.victor_tarnovski.banking.infra.box_messages.BoxMessageEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "outbox_messages")
@NoArgsConstructor
public class OutBoxMessageEntity extends BoxMessageEntity {
    public OutBoxMessageEntity(BoxMessageEntity message) {
        this.id = message.id;
        this.type = message.type;
        this.payload = message.payload;
        this.processedAt = message.processedAt;
    }    
}
