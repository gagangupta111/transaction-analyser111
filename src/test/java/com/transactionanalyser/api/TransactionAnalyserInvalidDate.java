package com.transactionanalyser.api;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.transactionanalyser.constants.ValidatorErrorCodes.DATE_IS_INVALID;

public class TransactionAnalyserInvalidDate {

    private static TransactionAnalyser transactionAnalyser;

    @BeforeClass
    public static void init(){
        transactionAnalyser = new TransactionAnalyser();
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void shouldThrowInvalidFromDateException(){

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(DATE_IS_INVALID);

       transactionAnalyser.analyseTransaction("valid_csv_1.csv",
               "20/08/2018 12:00",
               "20/08/2018 13:00:00",
               "Kwik-E-Mart");

    }

    @Test
    public void shouldThrowInvalidToDateException(){

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(DATE_IS_INVALID);

        transactionAnalyser.analyseTransaction("valid_csv_1.csv",
                "20/08/2018 12:00:00",
                "20/08/2018 13:00",
                "Kwik-E-Mart");

    }

}
