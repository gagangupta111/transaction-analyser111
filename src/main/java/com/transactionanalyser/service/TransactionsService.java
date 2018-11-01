package com.transactionanalyser.service;

import com.transactionanalyser.exception.BadCsvException;
import com.transactionanalyser.model.TransactionRecord;
import java.util.Date;
import java.util.List;

public interface TransactionsService {

    public List<TransactionRecord> getTransactionsBaseOnParameters(String path, Date fromDate, Date toDate, String merchant) throws BadCsvException;

}
