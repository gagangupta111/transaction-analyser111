package com.transactionanalyser.service;

import com.transactionanalyser.exception.BadCsvException;
import com.transactionanalyser.fileReader.FileReaderUtil;
import com.transactionanalyser.model.TransactionRecord;
import java.util.Date;
import java.util.List;

public interface TransactionsService {

    public List<TransactionRecord> getTransactionsBaseOnParameters(FileReaderUtil fileReaderUtil, Date fromDate, Date toDate, String merchant) throws BadCsvException;

}
