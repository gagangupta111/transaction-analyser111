package com.transactionanalyser.service;

import com.transactionanalyser.cache.TransactionRecordCacheService;
import com.transactionanalyser.fileReader.CSVFileReader;
import com.transactionanalyser.fileReader.FileReaderUtil;
import com.transactionanalyser.model.TransactionRecord;
import com.transactionanalyser.model.Type;
import com.transactionanalyser.validation.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionsServiceImpl implements TransactionsService{

    @Override
    public List<TransactionRecord> getTransactionsBaseOnParameters(String path, Date fromDate, Date toDate, String merchant) {

        path = path.trim();
        final String merchantName = merchant.trim();
        FileReaderUtil fileReaderUtil = new CSVFileReader(path);
        TransactionRecordCacheService service = new TransactionRecordCacheService(fileReaderUtil);
        service.initialize();
        List<TransactionRecord> list = service.getTRANSACTION_RECORDS();

        Validator.validateMerchantName(merchant, list);

        List<String> listOfReversalIds = new ArrayList<>();
        list = list.stream()
                .peek(record -> {
                    if (Type.REVERSAL.equals(record.getType())){
                        listOfReversalIds.add(record.getRelatedTransaction());
                    }
                })
                .filter(record -> record.getDate().after(fromDate) && record.getDate().before(toDate))
                .filter(record -> merchantName.equals(record.getMerchant()))
                .collect(Collectors.toList());

        return list.stream()
                .filter(record -> !listOfReversalIds.contains(record.getId())).collect(Collectors.toList());

    }

}
