package com.transactionanalyser.api;

import static org.junit.Assert.assertEquals;

import com.transactionanalyser.constants.ValidatorErrorCodes;
import org.junit.BeforeClass;
import org.junit.Test;

public class TransactionAnalyserTest {

    private static TransactionAnalyser transactionAnalyser;

    @BeforeClass
    public static void init(){
        transactionAnalyser = new TransactionAnalyser();
    }

    @Test
    public void shouldMatchWithExpectedResult(){

        String expectedResult = "Number of transactions = 1\n" +
                "Average Transaction Value = 59.99";

        String actualResult = transactionAnalyser.analyseTransaction("valid_csv_1.csv",
                "20/08/2018 12:00:00",
                "20/08/2018 13:00:00",
                "Kwik-E-Mart");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldMatchWithNoTransactionFound(){

        String actualResult = transactionAnalyser.analyseTransaction("valid_csv_1.csv",
                "20/08/2018 11:00:00",
                "20/08/2018 12:00:00",
                "Kwik-E-Mart");

        assertEquals(ValidatorErrorCodes.NO_TRANSACTION_FOUND, actualResult);
    }

}
