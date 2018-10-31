package com.transactionanalyser.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.util.Date;

public class TransactionRecord {

    private String id;
    private Date date;
    private Double amount;
    private String merchant;
    private Enum<Type> type;
    private String relatedTransaction;

    public TransactionRecord() {
    }

    public TransactionRecord(String id, Date date, Double amount, String merchant, Enum<Type> type, String relatedTransaction) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.merchant = merchant;
        this.type = type;
        this.relatedTransaction = relatedTransaction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public Enum<Type> getType() {
        return type;
    }

    public void setType(Enum<Type> type) {
        this.type = type;
    }

    public String getRelatedTransaction() {
        return relatedTransaction;
    }

    public void setRelatedTransaction(String relatedTransaction) {
        this.relatedTransaction = relatedTransaction;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TransactionRecord))
            return false;
        if (obj == this)
            return true;

        TransactionRecord rhs = (TransactionRecord) obj;
        return new EqualsBuilder().
                // if deriving: appendSuper(super.equals(obj)).
                        append(getId(), rhs.getId()).
                        append(getDate(), rhs.getDate()).
                        append(getAmount(), rhs.getAmount()).
                        append(getMerchant(), rhs.getMerchant()).
                        append(getType(), rhs.getType()).
                        append(getRelatedTransaction(), rhs.getRelatedTransaction()).
                        isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
                // if deriving: appendSuper(super.hashCode()).
                        append(getId()).
                        append(getDate()).
                        append(getAmount()).
                        append(getMerchant()).
                        append(getType()).
                        append(getRelatedTransaction()).
                        toHashCode();
    }
}
