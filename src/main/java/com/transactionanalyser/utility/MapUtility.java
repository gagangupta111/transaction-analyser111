package com.transactionanalyser.utility;

import static com.transactionanalyser.constants.ValidatorErrorCodes.NO_TRANSACTION_FOUND;
import com.transactionanalyser.model.TransactionRecord;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import static com.transactionanalyser.constants.Formats.AVEREGE_TRANSACTION_VALUE;
import static com.transactionanalyser.constants.Formats.NUMBER_OF_TRANSACTIONS;

public class MapUtility {

    public static String genericMapping(List<TransactionRecord> list){

        DoubleSummaryStatistics statistics = list.stream()
                .mapToDouble(value -> value.getAmount())
                .summaryStatistics();

        long count = statistics.getCount();

        if (count == 0){
            return NO_TRANSACTION_FOUND;
        }

        String output = NUMBER_OF_TRANSACTIONS + count + "\n"
                + AVEREGE_TRANSACTION_VALUE + statistics.getAverage();
        return output;
    }

}
