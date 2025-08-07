package com.victor_tarnovski.banking.application.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.victor_tarnovski.banking.domain.vo.Money;

public class MoneySerializer extends JsonSerializer<Money> {
    @Override
    public void serialize(Money money, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("amount", money.amount());
        gen.writeStringField("currency", money.currency().getCurrencyCode());
        gen.writeEndObject();
    }
    
}
