package com.transactionanalyser.validation;

import com.transactionanalyser.model.TransactionRecord;

import java.util.List;
import java.util.stream.Collectors;

public class Validation {

    public static final String MERCHANT_NAME_IS_NOT_VALID =
            "Merchant name is either empty or not found in records.";

    public static void validateMerchantName(String merchant, List<TransactionRecord> list){

        if (merchant == null || "".equals(merchant) ||
        list.stream().filter(record -> (record.getMerchant().equals(merchant)))
            .collect(Collectors.toList()).isEmpty()){
            throw new IllegalArgumentException(MERCHANT_NAME_IS_NOT_VALID);
        }
    }

}
