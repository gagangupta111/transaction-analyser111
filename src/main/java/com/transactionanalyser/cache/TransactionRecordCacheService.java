package com.transactionanalyser.cache;

import com.transactionanalyser.exception.BadCsvException;
import com.transactionanalyser.fileReader.FileReaderUtil;
import com.transactionanalyser.model.TransactionRecord;
import java.util.List;

public class TransactionRecordCacheService {

    private List<TransactionRecord> TRANSACTION_RECORDS;
    private FileReaderUtil fileReader;

    public TransactionRecordCacheService(FileReaderUtil fileReader) {
        this.fileReader = fileReader;
    }

    public void initialize() throws BadCsvException {

        TRANSACTION_RECORDS = fileReader.read();

    }

    public List<TransactionRecord> getTRANSACTION_RECORDS() {
        return TRANSACTION_RECORDS;
    }
}
