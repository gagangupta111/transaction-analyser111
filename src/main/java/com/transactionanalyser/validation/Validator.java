package com.transactionanalyser.validation;

import com.transactionanalyser.exception.BadCsvException;
import com.transactionanalyser.exception.BadInputException;
import com.transactionanalyser.model.TransactionRecord;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.transactionanalyser.constants.Formats.formatter;
import static com.transactionanalyser.constants.ValidatorErrorCodes.DATE_IS_INVALID;
import static com.transactionanalyser.constants.ValidatorErrorCodes.MERCHANT_NAME_INVALID;
import static com.transactionanalyser.constants.ValidatorErrorCodes.REVERSAL_ID_INVALID;

public class Validator {

    public static void validateMerchantName(String merchant, List<TransactionRecord> list){

        if (merchant == null || "".equals(merchant) ||
        list.stream().filter(record -> (record.getMerchant().equals(merchant)))
            .collect(Collectors.toList()).isEmpty()){
            throw new BadInputException(MERCHANT_NAME_INVALID);
        }
    }

    public static Date validateDate(String date){

        Date fDate;
        try {
            fDate = formatter.parse(date);
        } catch (ParseException e) {
            throw new BadInputException(DATE_IS_INVALID + date);
        }
        return fDate;
    }

    public static void validateReversalIds(List<String> reverseIds, List<String> paymentIds) throws BadCsvException {

       if (!paymentIds.containsAll(reverseIds)){
            throw new BadCsvException(REVERSAL_ID_INVALID);
       }
    }

}
