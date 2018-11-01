package com.transactionanalyser.api;

import com.transactionanalyser.exception.BadCsvException;
import com.transactionanalyser.model.TransactionRecord;
import com.transactionanalyser.service.TransactionsService;
import com.transactionanalyser.service.TransactionsServiceImpl;
import com.transactionanalyser.utility.MapUtility;
import static com.transactionanalyser.validation.Validator.validateDate;

import java.util.Date;
import java.util.List;

public class TransactionAnalyser {

    public String analyseTransaction(String path, String fromDate, String toDate, String merchant) throws BadCsvException {

        Date fDate = validateDate(fromDate);
        Date tDate = validateDate(toDate);

        TransactionsService transactionsService = new TransactionsServiceImpl();
        List<TransactionRecord> list = transactionsService.getTransactionsBaseOnParameters(path, fDate, tDate, merchant);

        return MapUtility.genericMapping(list);
    }

}
