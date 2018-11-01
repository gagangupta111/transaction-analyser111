package com.transactionanalyser.service;

import com.transactionanalyser.model.TransactionRecord;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.text.ParseException;
import java.util.List;

import static com.transactionanalyser.constants.Formats.formatter;
import static com.transactionanalyser.constants.ValidatorErrorCodes.AMOUNT_IS_INVALID;
import static com.transactionanalyser.constants.ValidatorErrorCodes.DATE_IS_INVALID;
import static com.transactionanalyser.constants.ValidatorErrorCodes.FILE_IS_INVALID;
import static com.transactionanalyser.constants.ValidatorErrorCodes.MERCHANT_NAME_INVALID;
import static com.transactionanalyser.constants.ValidatorErrorCodes.REVERSAL_ID_INVALID;
import static com.transactionanalyser.constants.ValidatorErrorCodes.TYPE_IS_INVALID;
import static com.transactionanalyser.validation.Validator.validateDate;

public class TransactionsServiceInValidCSVTests {

    private static TransactionsService service;

    @BeforeClass
    public static void init(){
        service = new TransactionsServiceImpl();
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void shouldThrowInvalidMerchantNameException() throws ParseException {

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(MERCHANT_NAME_INVALID);
        List<TransactionRecord> actualList = service.getTransactionsBaseOnParameters("valid_csv_1.csv",
                validateDate("20/08/2018 12:00:00"),
                validateDate("20/08/2018 13:00:00"),
                "INVALID");

    }

    @Test
    public void shouldThrowInvalidDateException() throws ParseException {

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(DATE_IS_INVALID + formatter.toPattern());
        List<TransactionRecord> actualList = service.getTransactionsBaseOnParameters("invalid_date_csv_1.csv",
                validateDate("20/08/2018 12:00:00"),
                validateDate("20/08/2018 13:00:00"),
                "Kwik-E-Mart");
    }

    @Test
    public void shouldThrowInvalidAmountException() throws ParseException {

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(AMOUNT_IS_INVALID);
        List<TransactionRecord> actualList = service.getTransactionsBaseOnParameters("invalid_amount_csv_2.csv",
                validateDate("20/08/2018 12:00:00"),
                validateDate("20/08/2018 13:00:00"),
                "Kwik-E-Mart");
    }

    @Test
    public void shouldThrowInvalidTypeException() throws ParseException {

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(TYPE_IS_INVALID);
        List<TransactionRecord> actualList = service.getTransactionsBaseOnParameters("invalid_type_csv_3.csv",
                validateDate("20/08/2018 12:00:00"),
                validateDate("20/08/2018 13:00:00"),
                "Kwik-E-Mart");
    }

    @Test
    public void shouldThrowInvalidFileNameOrPathException() throws ParseException {

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(FILE_IS_INVALID);
        List<TransactionRecord> actualList = service.getTransactionsBaseOnParameters("xxx.csv",
                validateDate("20/08/2018 12:00:00"),
                validateDate("20/08/2018 13:00:00"),
                "Kwik-E-Mart");
    }

    @Test
    public void shouldThrowInvalidReversalIdsException() throws ParseException {

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage(REVERSAL_ID_INVALID);
        List<TransactionRecord> actualList = service.getTransactionsBaseOnParameters("invalid_reversal_ids_4.csv",
                validateDate("20/08/2018 12:00:00"),
                validateDate("20/08/2018 13:00:00"),
                "Kwik-E-Mart");
    }
}
