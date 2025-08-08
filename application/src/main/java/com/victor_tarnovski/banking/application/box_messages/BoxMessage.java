package com.victor_tarnovski.banking.application.box_messages;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BoxMessage {
    private final BoxMessageId id;
    private final String type;
    private final String payload;
    private OffsetDateTime processedAt;

    public BoxMessage(String type, String payload) {
        this(null, type, payload);
    }

    public BoxMessage(BoxMessageId id, String type, String payload) {
        this.id = id;

        Objects.requireNonNull(type, "type must not be null");
        this.type = type;

        Objects.requireNonNull(payload, "paylaod must not be null");
        this.payload = payload;
    }

    public static BoxMessage of(Object o, ObjectMapper mapper) {
        String type = o.getClass().getTypeName(); 
        String payload;

        try {
            payload = mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } 

        return new BoxMessage(type, payload);
    } 

    public BoxMessageId id() {
        return id;
    }

    public String type() {
        return type;
    }

    public String payload() {
        return payload;
    }

    public Optional<OffsetDateTime> processedAt() {
        return Optional.ofNullable(processedAt);
    }

    public void process() {
        this.processedAt = Instant.now().atOffset(ZoneOffset.UTC);
    }

    public <U> U map(Function<? super BoxMessage, ? extends U> mapper) {
        return mapper.apply(this);
    }
}
