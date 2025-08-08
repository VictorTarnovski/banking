package com.victor_tarnovski.banking.application.box_messages;

import java.util.Objects;
import java.util.UUID;

public record BoxMessageId(UUID value) {
    public BoxMessageId {
        Objects.requireNonNull(value, "value must not be null");
    }
}
