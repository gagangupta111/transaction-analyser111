package com.transactionanalyser.fileReader;

import com.transactionanalyser.model.TransactionRecord;
import java.util.List;

public class CSVFileReader implements FileReaderUtil {

    private String csvFilePath;

    public CSVFileReader(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    @Override
    public List<TransactionRecord> read() {
        return null;
    }
}
