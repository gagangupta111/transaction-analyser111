package com.transactionanalyser.service;

import com.transactionanalyser.cache.TransactionRecordCacheService;
import com.transactionanalyser.exception.BadCsvException;
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
    public List<TransactionRecord> getTransactionsBaseOnParameters(FileReaderUtil fileReaderUtil, Date fromDate, Date toDate, String merchant) throws BadCsvException {

        final String merchantName = merchant.trim();
        TransactionRecordCacheService service = new TransactionRecordCacheService(fileReaderUtil);
        service.initialize();
        List<TransactionRecord> list = service.getTRANSACTION_RECORDS();

        Validator.validateMerchantName(merchant, list);

        List<String> listOfReversalIds = new ArrayList<>();
        List<String> paymentIds = new ArrayList<>();

        list = list.stream()
                .peek(record -> {
                    paymentIds.add(record.getId());
                    if (Type.REVERSAL.equals(record.getType())){
                        listOfReversalIds.add(record.getRelatedTransaction());
                    }
                })
                .filter(record -> Type.PAYMENT.equals(record.getType()))
                .filter(record -> record.getDate().after(fromDate) && record.getDate().before(toDate))
                .filter(record -> merchantName.equals(record.getMerchant()))
                .collect(Collectors.toList());

        Validator.validateReversalIds(listOfReversalIds, paymentIds);
        return list.stream()
                .filter(record -> !listOfReversalIds.contains(record.getId())).collect(Collectors.toList());

    }

}
