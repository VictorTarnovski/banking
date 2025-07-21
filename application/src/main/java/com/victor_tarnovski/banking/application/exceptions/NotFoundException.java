package com.victor_tarnovski.banking.application.exceptions;

public class NotFoundException extends ApplicationException {
  public NotFoundException(String resource, String id) {
    super(
      String.format(
        "%s[id=%s] not found", 
        resource.substring(0, 1).toUpperCase() + resource.substring(1), 
        id
      )
    );
  }  
}
