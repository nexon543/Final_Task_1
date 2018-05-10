package com.epam.provider.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * This is entity that contains transactions made by client{@link Profile}
 *
 * @author Gleb Aksenov
 */
public class Payment implements Entity, Serializable {

  private Integer transactionId;
  private Double amount;
  private Date date;

  /**
   * Client which made a transaction
   */
  private Integer idProfiles;

  public Payment() {
  }

  public Integer getTransactionId() {
    return transactionId;
  }

  public Payment setTransactionId(Integer transactionId) {
    this.transactionId = transactionId;
    return this;
  }

  public Double getAmount() {
    return amount;
  }

  public Payment setAmount(Double amount) {
    this.amount = amount;
    return this;
  }

  public Date getDate() {
    return date;
  }

  public Payment setDate(Date date) {
    this.date = date;
    return this;
  }

  public Integer getIdProfiles() {
    return idProfiles;
  }

  public Payment setIdProfiles(Integer idProfiles) {
    this.idProfiles = idProfiles;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Payment that = (Payment) o;
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
