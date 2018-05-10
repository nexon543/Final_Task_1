package com.epam.provider.model.fields;

public enum TariffField implements TableFieldName{

  ID("id_tariffs"),
  NAME("name"),
  PRICE("price"),
  RECIEVE_SPEED("recieving_speed"),
  TRANSFER_SPEED("transfer_speed"),
  DESCRIPTION("description"),
  LANG("lang");

  private final String name;

  TariffField(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

}
