package com.victor_tarnovski.banking.application.box_messages;

public class OutBoxMessage extends BoxMessage {
    public OutBoxMessage(BoxMessage message) { 
        super(message.type(), message.payload());
    }

    public OutBoxMessage(String type, String payload) {
        super(type, payload);
    }

    public OutBoxMessage(BoxMessageId id, String type, String payload) {
        super(id, type, payload);
    }
}
