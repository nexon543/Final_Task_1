package com.epam.provider.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Gleb Aksenov
 */
public class Tariff implements Entity, Serializable {

  private Integer tariffId;
  private Integer price;
  private Integer receivingSpeed;
  private Integer transferSpeed;
  private String name;
  private String description;
  private String lang;

  public Tariff() {
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Tariff tariff = (Tariff) o;
    return Objects.equals(tariffId, tariff.tariffId) &&
        Objects.equals(price, tariff.price) &&
        Objects.equals(receivingSpeed, tariff.receivingSpeed) &&
        Objects.equals(name, tariff.name);
  }

  public Integer getTariffId() {
    return tariffId;
  }

  public Tariff setTariffId(Integer tariffId) {
    this.tariffId = tariffId;
    return this;
  }

  public Integer getPrice() {
    return price;
  }

  public Tariff setPrice(Integer price) {
    this.price = price;
    return this;
  }

  public Integer getReceivingSpeed() {
    return receivingSpeed;
  }

  public Tariff setReceivingSpeed(Integer receivingSpeed) {
    this.receivingSpeed = receivingSpeed;
    return this;
  }

  public Integer getTransferSpeed() {
    return transferSpeed;
  }

  public Tariff setTransferSpeed(Integer transferSpeed) {
    this.transferSpeed = transferSpeed;
    return this;
  }

  public String getName() {
    return name;
  }

  public Tariff setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Tariff setDescription(String description) {
    this.description = description;
    return this;
  }

  public String getLang() {
    return lang;
  }

  public Tariff setLang(String lang) {
    this.lang = lang;
    return this;
  }

  @Override
  public int hashCode() {
    return Objects.hash(tariffId, price, receivingSpeed, name);
  }

  @Override
  public String toString() {
    return "TariffField{" +
        "tariffId=" + tariffId +
        ", price=" + price +
        ", receivingSpeed=" + receivingSpeed +
        ", name='" + name + '\'' +
        '}';
  }
}
