package com.transactionanalyser.api;

import com.transactionanalyser.model.TransactionRecord;
import com.transactionanalyser.service.TransactionsService;
import com.transactionanalyser.service.TransactionsServiceImpl;
import com.transactionanalyser.utility.MapUtility;

import static com.transactionanalyser.constants.ValidatorErrorCodes.DATE_IS_INVALID;
import static com.transactionanalyser.constants.Formats.formatter;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class TransactionAnalyser {

    public String analyseTransaction(String path, String fromDate, String toDate, String merchant){

        Date fDate;
        Date tDate;
        try {
            fDate = formatter.parse(fromDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException(DATE_IS_INVALID + fromDate);
        }
        try {
            tDate = formatter.parse(toDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException(DATE_IS_INVALID + toDate);
        }

        TransactionsService transactionsService = new TransactionsServiceImpl();
        List<TransactionRecord> list = transactionsService.getTransactionsBaseOnParameters(path, fDate, tDate, merchant);

        return MapUtility.genericMapping(list);
    }

}
