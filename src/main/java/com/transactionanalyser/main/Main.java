package com.transactionanalyser.main;

import com.transactionanalyser.api.TransactionAnalyser;
import com.transactionanalyser.exception.BadCsvException;

public class Main {

    public static void main(String[] args){

        TransactionAnalyser transactionAnalyser = new TransactionAnalyser();

        String actualResult = null;
        try {
            actualResult = transactionAnalyser.analyseTransaction("valid_csv_1.csv",
                    "20/08/2018 12:00:00",
                    "20/08/2018 14:00:00",
                    "MacLaren");
        } catch (BadCsvException e) {
            e.printStackTrace();
        }

        System.out.println(actualResult);

    }

}
