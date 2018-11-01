package com.transactionanalyser.fileReader;

import com.transactionanalyser.exception.BadCsvException;
import com.transactionanalyser.model.TransactionRecord;

import java.util.List;

public interface FileReaderUtil {

    public List<TransactionRecord> read() throws BadCsvException;

}
