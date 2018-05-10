package com.epam.provider.model.fields;

public enum ProfileField implements TableFieldName{
  
  ID("id_profiles"),
  FIRST_NAME("first_name"),
  BALANCE("balance"),
  PASSPORT("passport"),
  REGISTR_DATE("register_date"),
  SECOND_NAME("second_name"),
  ID_TARIFFS("id_tariffs"),
  LOGIN("login"),
  PASS("pass"),
  ROLE("role");

  private final String name;

  ProfileField(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
