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

    public Tariff(){}

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTariffId() {
        return tariffId;
    }

    public void setTariffId(Integer tariffId) {
        this.tariffId = tariffId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getReceivingSpeed() {
        return receivingSpeed;
    }

    public void setReceivingSpeed(Integer receivingSpeed) {
        this.receivingSpeed = receivingSpeed;
    }

    public Integer getTransferSpeed() {
        return transferSpeed;
    }

    public void setTransferSpeed(Integer transferSpeed) {
        this.transferSpeed = transferSpeed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tariff tariff = (Tariff) o;
        return Objects.equals(tariffId, tariff.tariffId) &&
                Objects.equals(price, tariff.price) &&
                Objects.equals(receivingSpeed, tariff.receivingSpeed) &&
                Objects.equals(name, tariff.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tariffId, price, receivingSpeed, name);
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "tariffId=" + tariffId +
                ", price=" + price +
                ", receivingSpeed=" + receivingSpeed +
                ", name='" + name + '\'' +
                '}';
    }
}
