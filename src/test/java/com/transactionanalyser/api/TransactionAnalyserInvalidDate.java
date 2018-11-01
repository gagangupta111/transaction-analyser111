package com.transactionanalyser.api;

import com.transactionanalyser.exception.BadCsvException;
import com.transactionanalyser.exception.BadInputException;
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
    public void shouldThrowInvalidFromDateException() throws BadCsvException {

        expectedEx.expect(BadInputException.class);
        expectedEx.expectMessage(DATE_IS_INVALID);

       transactionAnalyser.analyseTransaction("valid_csv_1.csv",
               "20/08/2018 12:00",
               "20/08/2018 13:00:00",
               "Kwik-E-Mart");

    }

    @Test
    public void shouldThrowInvalidToDateException() throws BadCsvException {

        expectedEx.expect(BadInputException.class);
        expectedEx.expectMessage(DATE_IS_INVALID);

        transactionAnalyser.analyseTransaction("valid_csv_1.csv",
                "20/08/2018 12:00:00",
                "20/08/2018 13:00",
                "Kwik-E-Mart");

    }

}
