package com.transactionanalyser.service;

import com.transactionanalyser.cache.TransactionRecordCacheService;
import com.transactionanalyser.fileReader.CSVFileReader;
import com.transactionanalyser.fileReader.FileReaderUtil;
import com.transactionanalyser.model.TransactionRecord;
import com.transactionanalyser.validation.Validation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionsServiceImpl implements TransactionsService{

    @Override
    public List<TransactionRecord> getTransactionsBaseOnParameters(String path, Date fromDate, Date toDate, String merchant) {

        FileReaderUtil fileReaderUtil = new CSVFileReader(path);
        TransactionRecordCacheService service = new TransactionRecordCacheService(fileReaderUtil);
        service.initialize();
        List<TransactionRecord> list = service.getTRANSACTION_RECORDS();

        Validation.validateMerchantName(merchant, list);

        return list.stream()
                .filter(record -> fromDate.after(record.getDate()) && toDate.before(record.getDate()))
                .filter(record -> merchant.equals(record.getMerchant())).
                collect(Collectors.toList());

    }

}
