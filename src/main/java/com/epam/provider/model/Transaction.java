package com.epam.provider.model;

import java.sql.Date;
import java.util.Objects;

/**
 * @author Gleb Aksenov
 */
public class Transaction implements Entity {
    private Integer transactionId;
    private Integer amount;
    private Date date;

    /**
     * Client which made a transaction
     */
    private Integer idProfiles;

    public Integer getTransactionId() {
        return transactionId;
    }

    public Transaction setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public Transaction setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Transaction setDate(Date date) {
        this.date = date;
        return this;
    }

    public Integer getIdProfiles() {
        return idProfiles;
    }

    public Transaction setIdProfiles(Integer idProfiles) {
        this.idProfiles = idProfiles;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(transactionId, that.transactionId) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(date, that.date) &&
                Objects.equals(idProfiles, that.idProfiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, amount, date, idProfiles);
    }
}
