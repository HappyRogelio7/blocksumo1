package com.zittla.blocksumo.commons.model;

import java.util.Set;
import java.util.UUID;

public class User {

  private final UUID id;
  private String name;

  public User(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
