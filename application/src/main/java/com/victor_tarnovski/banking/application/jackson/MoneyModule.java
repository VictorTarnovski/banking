package com.victor_tarnovski.banking.application.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.victor_tarnovski.banking.application.jackson.deserializers.MoneyDeserializer;
import com.victor_tarnovski.banking.application.jackson.serializers.MoneySerializer;
import com.victor_tarnovski.banking.domain.vo.Money;

public class MoneyModule extends SimpleModule {
   public MoneyModule() {
     addSerializer(Money.class, new MoneySerializer());
     addDeserializer(Money.class, new MoneyDeserializer());
   } 
}
