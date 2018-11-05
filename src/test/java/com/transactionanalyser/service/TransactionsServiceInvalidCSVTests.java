package com.transactionanalyser.service;

import com.transactionanalyser.exception.BadCsvException;
import com.transactionanalyser.exception.BadInputException;
import com.transactionanalyser.fileReader.CSVFileReader;
import com.transactionanalyser.fileReader.FileReaderUtil;
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

public class TransactionsServiceInvalidCSVTests {

    private static TransactionsService service;

    @BeforeClass
    public static void init(){
        service = new TransactionsServiceImpl();
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void shouldThrowInvalidMerchantNameException() throws BadCsvException {

        expectedEx.expect(BadInputException.class);
        expectedEx.expectMessage(MERCHANT_NAME_INVALID);

        FileReaderUtil fileReaderUtil = new CSVFileReader("valid_csv_1.csv");
        List<TransactionRecord> actualList = service.getTransactionsBaseOnParameters(fileReaderUtil,
                validateDate("20/08/2018 12:00:00"),
                validateDate("20/08/2018 13:00:00"),
                "INVALID");

    }

    @Test
    public void shouldThrowBadCsvExceptionAsDateInvalid() throws BadCsvException {

        expectedEx.expect(BadCsvException.class);
        expectedEx.expectMessage(DATE_IS_INVALID + formatter.toPattern());

        FileReaderUtil fileReaderUtil = new CSVFileReader("invalid_date_csv_1.csv");
        List<TransactionRecord> actualList = service.getTransactionsBaseOnParameters(fileReaderUtil,
                validateDate("20/08/2018 12:00:00"),
                validateDate("20/08/2018 13:00:00"),
                "Kwik-E-Mart");
    }

    @Test
    public void shouldThrowBadCsvExceptionAsAmountInvalid() throws BadCsvException {

        expectedEx.expect(BadCsvException.class);
        expectedEx.expectMessage(AMOUNT_IS_INVALID);

        FileReaderUtil fileReaderUtil = new CSVFileReader("invalid_amount_csv_2.csv");
        List<TransactionRecord> actualList = service.getTransactionsBaseOnParameters(fileReaderUtil,
                validateDate("20/08/2018 12:00:00"),
                validateDate("20/08/2018 13:00:00"),
                "Kwik-E-Mart");
    }

    @Test
    public void shouldThrowBadCsvExceptionAsTypeInvalid() throws BadCsvException {

        expectedEx.expect(BadCsvException.class);
        expectedEx.expectMessage(TYPE_IS_INVALID);

        FileReaderUtil fileReaderUtil = new CSVFileReader("invalid_type_csv_3.csv");
        List<TransactionRecord> actualList = service.getTransactionsBaseOnParameters(fileReaderUtil,
                validateDate("20/08/2018 12:00:00"),
                validateDate("20/08/2018 13:00:00"),
                "Kwik-E-Mart");
    }

    @Test
    public void shouldThrowBadCsvExceptionAsFileInvalid() throws BadCsvException {

        expectedEx.expect(BadInputException.class);
        expectedEx.expectMessage(FILE_IS_INVALID);

        FileReaderUtil fileReaderUtil = new CSVFileReader("xxx.csv");
        List<TransactionRecord> actualList = service.getTransactionsBaseOnParameters(fileReaderUtil,
                validateDate("20/08/2018 12:00:00"),
                validateDate("20/08/2018 13:00:00"),
                "Kwik-E-Mart");
    }

    @Test
    public void shouldThrowBadCsvExceptionAsInvalidReversalIds() throws BadCsvException {

        expectedEx.expect(BadCsvException.class);
        expectedEx.expectMessage(REVERSAL_ID_INVALID);

        FileReaderUtil fileReaderUtil = new CSVFileReader("invalid_reversal_ids_4.csv");
        List<TransactionRecord> actualList = service.getTransactionsBaseOnParameters(fileReaderUtil,
                validateDate("20/08/2018 12:00:00"),
                validateDate("20/08/2018 13:00:00"),
                "Kwik-E-Mart");
    }
}
