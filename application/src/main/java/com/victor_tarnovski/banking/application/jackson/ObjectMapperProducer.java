package com.victor_tarnovski.banking.application.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.victor_tarnovski.banking.application.jackson.deserializers.MoneyDeserializer;
import com.victor_tarnovski.banking.application.jackson.serializers.MoneySerializer;
import com.victor_tarnovski.banking.domain.vo.Money;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class ObjectMapperProducer {
    @Produces
    public ObjectMapper objectMapper() {
        var mapper = new ObjectMapper();
        var module = new SimpleModule();
        module.addSerializer(Money.class, new MoneySerializer());
        module.addDeserializer(Money.class, new MoneyDeserializer());
        mapper.registerModule(module);

        return mapper;
    }
}