package com.transactionanalyser.main;

import com.transactionanalyser.api.TransactionAnalyser;
import com.transactionanalyser.exception.BadCsvException;
import com.transactionanalyser.fileReader.CSVFileReader;
import com.transactionanalyser.fileReader.FileReaderUtil;

public class Main {

    public static void main(String[] args){

        FileReaderUtil fileReaderUtil = new CSVFileReader("valid_csv_1.csv");
        TransactionAnalyser transactionAnalyser = new TransactionAnalyser(fileReaderUtil);

        String actualResult = null;
        try {
            actualResult = transactionAnalyser.analyseTransaction("20/08/2018 12:00:00",
                    "20/08/2018 14:00:00",
                    "MacLaren");
        } catch (BadCsvException e) {
            e.printStackTrace();
        }

        System.out.println(actualResult);

    }

}
