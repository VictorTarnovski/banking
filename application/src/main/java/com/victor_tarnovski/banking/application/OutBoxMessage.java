package com.victor_tarnovski.banking.application;

public class OutBoxMessage extends BoxMessage {
    public OutBoxMessage(BoxMessage message) { 
        super(message.type(), message.payload());
    }
}
