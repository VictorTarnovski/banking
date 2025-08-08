package com.victor_tarnovski.banking.application.box_messages;

public class OutBoxMessage extends BoxMessage {
    public OutBoxMessage(BoxMessage message) { 
        super(message.type(), message.payload());
    }
}
