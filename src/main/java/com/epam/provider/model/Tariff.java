package com.epam.provider.model;

import java.util.Objects;

/**
 * Created by HP on 27.03.2018.
 */
public class Tariff implements Entity {
    private Integer idTarifs;
    private Integer price;
    private Integer recievingSpeed;
    private Integer transferSpeed;
    private String name;
    private Boolean isUnlim;
    private String description;
    private String lang;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Boolean getUnlim() {
        return isUnlim;
    }

    public void setUnlim(Boolean unlim) {
        isUnlim = unlim;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descriprion) {
        this.description = descriprion;
    }

    public Integer getIdTarifs() {
        return idTarifs;
    }

    public void setIdTarifs(Integer idTarifs) {
        this.idTarifs = idTarifs;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRecievingSpeed() {
        return recievingSpeed;
    }

    public void setRecievingSpeed(Integer recievingSpeed) {
        this.recievingSpeed = recievingSpeed;
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
        return Objects.equals(idTarifs, tariff.idTarifs) &&
                Objects.equals(price, tariff.price) &&
                Objects.equals(recievingSpeed, tariff.recievingSpeed) &&
                Objects.equals(name, tariff.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTarifs, price, recievingSpeed, name);
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "idTarifs=" + idTarifs +
                ", price=" + price +
                ", recievingSpeed=" + recievingSpeed +
                ", name='" + name + '\'' +
                '}';
    }
}
