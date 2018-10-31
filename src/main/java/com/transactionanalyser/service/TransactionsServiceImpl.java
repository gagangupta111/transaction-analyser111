package com.transactionanalyser.service;

import com.transactionanalyser.cache.TransactionRecordCacheService;
import com.transactionanalyser.fileReader.CSVFileReader;
import com.transactionanalyser.fileReader.FileReaderUtil;
import com.transactionanalyser.model.TransactionRecord;

import java.util.Date;
import java.util.List;

public class TransactionsServiceImpl implements TransactionsService{

    @Override
    public List<TransactionRecord> getTransactionsBaseOnParameters(String path, Date fromDate, Date toDate, String merchant) {

        FileReaderUtil fileReaderUtil = new CSVFileReader(path);
        TransactionRecordCacheService service = new TransactionRecordCacheService(fileReaderUtil);
        service.initialize();
        List<TransactionRecord> list = service.getTRANSACTION_RECORDS();



        return null;
    }

}
