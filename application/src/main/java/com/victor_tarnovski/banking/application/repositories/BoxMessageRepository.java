package com.victor_tarnovski.banking.application.repositories;

import java.util.List;

import com.victor_tarnovski.banking.application.box_messages.BoxMessage;

public interface BoxMessageRepository<T extends BoxMessage> {
   void create(final T message); 
   List<T> findUnprocessedByType(final String type); 
   void update(final T message);
}
