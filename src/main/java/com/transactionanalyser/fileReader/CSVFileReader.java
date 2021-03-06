package com.transactionanalyser.fileReader;

import com.opencsv.CSVReader;
import static com.transactionanalyser.constants.Formats.formatter;
import static com.transactionanalyser.constants.ValidatorErrorCodes.DATE_IS_INVALID;
import static com.transactionanalyser.constants.ValidatorErrorCodes.AMOUNT_IS_INVALID;
import static com.transactionanalyser.constants.ValidatorErrorCodes.FILE_IS_INVALID;
import static com.transactionanalyser.constants.ValidatorErrorCodes.TYPE_IS_INVALID;

import com.transactionanalyser.exception.BadCsvException;
import com.transactionanalyser.exception.BadInputException;
import com.transactionanalyser.model.TransactionRecord;
import com.transactionanalyser.model.Type;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSVFileReader implements FileReaderUtil {

    private String csvFilePath;

    public CSVFileReader(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    @Override
    public List<TransactionRecord> read() throws BadCsvException {

            List<TransactionRecord> list = new ArrayList<>();

            CSVReader reader = null;
            try {
                reader = new CSVReader(new FileReader(Thread.currentThread().getContextClassLoader().getResource(csvFilePath).getFile()));
                Date date;
                Double amount;
                String[] line;
                Type type;
                while ((line = reader.readNext()) != null) {
                    try {
                        date = formatter.parse(line[1].trim());
                    } catch (ParseException e) {
                        throw new BadCsvException(DATE_IS_INVALID + formatter.toPattern() + " " + line[1]);
                    }

                    try {
                        amount = Double.parseDouble(line[2].trim());
                    } catch (NumberFormatException e) {
                        throw new BadCsvException(AMOUNT_IS_INVALID + line[2]);
                    }

                    try {
                        type = Type.valueOf(line[4].trim());
                    }catch (IllegalArgumentException e){
                        throw new BadCsvException(TYPE_IS_INVALID + line[4]);
                    }

                    TransactionRecord record = new TransactionRecord(line[0].trim(), date, amount, line[3].trim(), type, line[5].trim());
                    list.add(record);

                }

            } catch (FileNotFoundException | NullPointerException e) {
                throw new BadInputException(FILE_IS_INVALID + csvFilePath);
            } catch (BadCsvException e) {
                throw e;
            } catch (IOException e) {
                throw new BadCsvException(FILE_IS_INVALID + csvFilePath);
            }

        return list;
    }
}
