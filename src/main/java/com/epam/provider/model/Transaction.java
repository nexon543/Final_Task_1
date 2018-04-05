package com.epam.provider.model;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;


public class Transaction implements Entity {
    private Integer idTransactions;
    private Integer amount;
    private Date date;
    private Integer idProfiles;

    public Integer getIdTransactions() {
        return idTransactions;
    }

    public void setIdTransactions(Integer idTransactions) {
        this.idTransactions = idTransactions;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getIdProfiles() {
        return idProfiles;
    }

    public void setIdProfiles(Integer idProfiles) {
        this.idProfiles = idProfiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(idTransactions, that.idTransactions) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(date, that.date) &&
                Objects.equals(idProfiles, that.idProfiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransactions, amount, date, idProfiles);
    }
}
