package com.transactionanalyser.model;

public enum Type {

    PAYMENT("PAYMENT"),
    REVERSAL("REVERSAL");

    private String type;

    Type(String type) {
        this.type = type;
    }

}
