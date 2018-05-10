package com.epam.provider.model.fields;

public enum PaymentField implements TableFieldName{

  AMOUNT("amount"),
  DATE("date"),
  ID_TRANSACTIONS("id_transactions"),
  ID_PROFILES("id_profile");
  
  private final String name;

  PaymentField(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
