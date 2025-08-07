package com.victor_tarnovski.banking.application.repositories;

import com.victor_tarnovski.banking.application.BoxMessage;

public interface BoxMessageRepository<T extends BoxMessage> {
   public void create(final T message); 
}
