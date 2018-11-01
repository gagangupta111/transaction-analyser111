package com.transactionanalyser.test;

import static com.transactionanalyser.constants.ValidatorErrorCodes.MERCHANT_NAME_IS_NOT_VALID;
import com.transactionanalyser.model.TransactionRecord;
import com.transactionanalyser.model.Type;
import com.transactionanalyser.service.TransactionsService;
import com.transactionanalyser.service.TransactionsServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static com.transactionanalyser.constants.Formats.formatter;

public class ValidCSVTest {

    @Test
    public void shouldPassWithOneResultReturnedBetweenDates() throws ParseException {

        List<TransactionRecord> expectedList = new ArrayList<>();
        expectedList.add(new TransactionRecord("WLMFRDGD", formatter.parse("20/08/2018 12:45:33"),
                59.99, "Kwik-E-Mart", Type.valueOf("PAYMENT"), ""));

        TransactionsService service = new TransactionsServiceImpl();
        List<TransactionRecord> actualList = service.getTransactionsBaseOnParameters("valid_csv_1.csv",
                formatter.parse("20/08/2018 12:00:00"),
                formatter.parse("20/08/2018 13:00:00"),
                "Kwik-E-Mart");

        assertEquals(expectedList, actualList);

    }

    @Test
    public void shouldPassWithNoResultReturnedBetweenDates() throws ParseException {

        List<TransactionRecord> expectedList = new ArrayList<>();

        TransactionsService service = new TransactionsServiceImpl();
        List<TransactionRecord> actualList = service.getTransactionsBaseOnParameters("valid_csv_1.csv",
                formatter.parse("20/08/2018 11:00:00"),
                formatter.parse("20/08/2018 12:00:00"),
                "Kwik-E-Mart");

        assertEquals(expectedList, actualList);

    }

}
