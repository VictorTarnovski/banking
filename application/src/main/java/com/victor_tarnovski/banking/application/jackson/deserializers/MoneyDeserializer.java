package com.victor_tarnovski.banking.application.jackson.deserializers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.victor_tarnovski.banking.domain.vo.Money;

public class MoneyDeserializer extends JsonDeserializer<Money> {
    @Override
    public Money deserialize(JsonParser parser, DeserializationContext ctx) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        BigDecimal amount = new BigDecimal(node.get("amount").asText());
        Currency currency = Currency.getInstance(node.get("currency").asText());
        return new Money(amount, currency);
    }
}
