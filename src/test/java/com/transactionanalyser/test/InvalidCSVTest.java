package com.transactionanalyser.test;

import com.transactionanalyser.model.TransactionRecord;
import com.transactionanalyser.service.TransactionsService;
import com.transactionanalyser.service.TransactionsServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.text.ParseException;
import java.util.List;

import static com.transactionanalyser.constants.Formats.formatter;
import static com.transactionanalyser.constants.ValidatorErrorCodes.AMOUNT_IS_INVALID;
import static com.transactionanalyser.constants.ValidatorErrorCodes.CSV_DATE_IS_INVALID;
import static com.transactionanalyser.constants.ValidatorErrorCodes.FILE_IS_INVALID;
import static com.transactionanalyser.constants.ValidatorErrorCodes.MERCHANT_NAME_IS_NOT_VALID;
import static com.transactionanalyser.constants.ValidatorErrorCodes.TYPE_IS_INVALID;

public class InvalidCSVTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void shouldThrowInvalidMerchantNameException() throws ParseException {

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(MERCHANT_NAME_IS_NOT_VALID);
        TransactionsService service = new TransactionsServiceImpl();
        List<TransactionRecord> actualList = service.getTransactionsBaseOnParameters("valid_csv_1.csv",
                formatter.parse("20/08/2018 12:00:00"),
                formatter.parse("20/08/2018 13:00:00"),
                "INVALID");

    }

    @Test
    public void shouldThrowInvalidDateException() throws ParseException {

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(CSV_DATE_IS_INVALID + formatter.toPattern());
        TransactionsService service = new TransactionsServiceImpl();
        List<TransactionRecord> actualList = service.getTransactionsBaseOnParameters("invalid_date_csv_1.csv",
                formatter.parse("20/08/2018 12:00:00"),
                formatter.parse("20/08/2018 13:00:00"),
                "Kwik-E-Mart");
    }

    @Test
    public void shouldThrowInvalidAmountException() throws ParseException {

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(AMOUNT_IS_INVALID);
        TransactionsService service = new TransactionsServiceImpl();
        List<TransactionRecord> actualList = service.getTransactionsBaseOnParameters("invalid_amount_csv_2.csv",
                formatter.parse("20/08/2018 12:00:00"),
                formatter.parse("20/08/2018 13:00:00"),
                "Kwik-E-Mart");
    }

    @Test
    public void shouldThrowInvalidTypeException() throws ParseException {

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(TYPE_IS_INVALID);
        TransactionsService service = new TransactionsServiceImpl();
        List<TransactionRecord> actualList = service.getTransactionsBaseOnParameters("invalid_type_csv_3.csv",
                formatter.parse("20/08/2018 12:00:00"),
                formatter.parse("20/08/2018 13:00:00"),
                "Kwik-E-Mart");
    }

    @Test
    public void shouldThrowInvalidFileNameOrPathException() throws ParseException {

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(FILE_IS_INVALID);
        TransactionsService service = new TransactionsServiceImpl();
        List<TransactionRecord> actualList = service.getTransactionsBaseOnParameters("xxx.csv",
                formatter.parse("20/08/2018 12:00:00"),
                formatter.parse("20/08/2018 13:00:00"),
                "Kwik-E-Mart");
    }
}
