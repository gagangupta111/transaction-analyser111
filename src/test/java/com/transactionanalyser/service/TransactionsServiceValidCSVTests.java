package com.transactionanalyser.service;

import com.transactionanalyser.exception.BadCsvException;
import com.transactionanalyser.fileReader.CSVFileReader;
import com.transactionanalyser.fileReader.FileReaderUtil;
import com.transactionanalyser.model.TransactionRecord;
import com.transactionanalyser.model.Type;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static com.transactionanalyser.validation.Validator.validateDate;

public class TransactionsServiceValidCSVTests {

    private static TransactionsService service;

    @BeforeClass
    public static void init(){
        service = new TransactionsServiceImpl();
    }
    @Test
    public void shouldPassWithOneResultReturnedBetweenDates() throws BadCsvException {

        List<TransactionRecord> expectedList = new ArrayList<>();
        expectedList.add(new TransactionRecord("WLMFRDGD", validateDate("20/08/2018 12:45:33"),
                59.99, "Kwik-E-Mart", Type.valueOf("PAYMENT"), ""));

        FileReaderUtil fileReaderUtil = new CSVFileReader("valid_csv_1.csv");
        List<TransactionRecord> actualList = service.getTransactionsBaseOnParameters(fileReaderUtil,
                validateDate("20/08/2018 12:00:00"),
                validateDate("20/08/2018 13:00:00"),
                "Kwik-E-Mart");

        assertEquals(expectedList, actualList);

    }

    @Test
    public void shouldMatchWithExpectedResultMacLaren() throws BadCsvException {

        List<TransactionRecord> expectedList = new ArrayList<>();
        expectedList.add(new TransactionRecord("LFVCTEYM", validateDate("20/08/2018 12:50:02"),
                5.0, "MacLaren", Type.valueOf("PAYMENT"), ""));

        FileReaderUtil fileReaderUtil = new CSVFileReader("valid_csv_1.csv");
        List<TransactionRecord> actualList = service.getTransactionsBaseOnParameters(fileReaderUtil,
                validateDate("20/08/2018 12:00:00"),
                validateDate("20/08/2018 14:00:00"),
                "MacLaren");

        assertEquals(expectedList, actualList);
    }

    @Test
    public void shouldPassWithNoResultReturnedBetweenDates() throws ParseException, BadCsvException {

        List<TransactionRecord> expectedList = new ArrayList<>();

        FileReaderUtil fileReaderUtil = new CSVFileReader("valid_csv_1.csv");
        List<TransactionRecord> actualList = service.getTransactionsBaseOnParameters(fileReaderUtil,
                validateDate("20/08/2018 11:00:00"),
                validateDate("20/08/2018 12:00:00"),
                "Kwik-E-Mart");

        assertEquals(expectedList, actualList);

    }

}
