package com.transactionanalyser.api;

import com.transactionanalyser.exception.BadCsvException;
import com.transactionanalyser.fileReader.CSVFileReader;
import com.transactionanalyser.fileReader.FileReaderUtil;
import com.transactionanalyser.model.TransactionRecord;
import com.transactionanalyser.service.TransactionsService;
import com.transactionanalyser.service.TransactionsServiceImpl;
import com.transactionanalyser.utility.MapUtility;
import static com.transactionanalyser.validation.Validator.validateDate;

import java.util.Date;
import java.util.List;

public class TransactionAnalyser {

    private FileReaderUtil readerUtil;

    public TransactionAnalyser() {
        readerUtil = new CSVFileReader("valid_csv_1.csv");
    }

    public TransactionAnalyser(FileReaderUtil readerUtil) {
        this.readerUtil = readerUtil;
    }

    public String analyseTransaction(String fromDate, String toDate, String merchant) throws BadCsvException {

        Date fDate = validateDate(fromDate);
        Date tDate = validateDate(toDate);

        TransactionsService transactionsService = new TransactionsServiceImpl();
        List<TransactionRecord> list = transactionsService.getTransactionsBaseOnParameters(readerUtil, fDate, tDate, merchant);

        return MapUtility.genericMapping(list);
    }

}
