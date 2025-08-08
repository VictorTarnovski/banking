package com.victor_tarnovski.banking.application.box_messages;

import java.util.function.Function;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BoxMessage {
    private final String type;
    private final String payload;
    
    public BoxMessage(String type, String payload) {
        this.type = type;
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

    public String type() {
        return type;
    }

    public String payload() {
        return payload;
    }

    public <U> U map(Function<? super BoxMessage, ? extends U> mapper) {
        return mapper.apply(this);
    }
}
