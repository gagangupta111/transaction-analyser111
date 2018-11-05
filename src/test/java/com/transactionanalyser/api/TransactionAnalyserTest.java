package com.transactionanalyser.api;

import static org.junit.Assert.assertEquals;

import com.transactionanalyser.constants.ValidatorErrorCodes;
import com.transactionanalyser.exception.BadCsvException;
import com.transactionanalyser.fileReader.CSVFileReader;
import com.transactionanalyser.fileReader.FileReaderUtil;
import org.junit.BeforeClass;
import org.junit.Test;

public class TransactionAnalyserTest {

    private static TransactionAnalyser transactionAnalyser;

    @BeforeClass
    public static void init(){
        FileReaderUtil fileReaderUtil = new CSVFileReader("valid_csv_1.csv");
        transactionAnalyser = new TransactionAnalyser();
    }

    @Test
    public void shouldMatchWithExpectedResult() throws BadCsvException {

        String expectedResult = "Number of transactions = 1\n" +
                "Average Transaction Value = 59.99";

        String actualResult = transactionAnalyser.analyseTransaction("20/08/2018 12:00:00",
                "20/08/2018 13:00:00",
                "Kwik-E-Mart");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldMatchWithExpectedResultMacLaren() throws BadCsvException {

        String expectedResult = "Number of transactions = 1\n" +
                "Average Transaction Value = 5.0";

        String actualResult = transactionAnalyser.analyseTransaction("20/08/2018 12:00:00",
                "20/08/2018 14:00:00",
                "MacLaren");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldMatchWithNoTransactionFound() throws BadCsvException {

        String actualResult = transactionAnalyser.analyseTransaction("20/08/2018 11:00:00",
                "20/08/2018 12:00:00",
                "Kwik-E-Mart");

        assertEquals(ValidatorErrorCodes.NO_TRANSACTION_FOUND, actualResult);
    }

}
