package com.transactionanalyser.fileReader;

import com.opencsv.CSVReader;
import com.transactionanalyser.model.TransactionRecord;
import com.transactionanalyser.model.Type;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSVFileReader implements FileReaderUtil {

    private String csvFilePath;
    public static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
    public static final String CSV_DATE_IS_INVALID = " The value of Date is not as per accepted format ";
    public static final String AMOUNT_IS_INVALID = "The value of Amount is not valid for : ";
    public static final String TYPE_IS_INVALID = "The value of Type is not valid for : ";

    public CSVFileReader(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    @Override
    public List<TransactionRecord> read() {

            String csvFile = "valid_csv_1.csv";
            List<TransactionRecord> list = new ArrayList<>();

            CSVReader reader = null;
            try {
                reader = new CSVReader(new FileReader(csvFile));
                Date date;
                Double amount;
                String[] line;
                Type type;
                while ((line = reader.readNext()) != null) {
                    try {
                        date = formatter.parse(line[1]);
                    } catch (ParseException e) {
                        throw new IllegalArgumentException(CSV_DATE_IS_INVALID + formatter + " " + line[1]);
                    }

                    try {
                        amount = Double.parseDouble(line[2]);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException(AMOUNT_IS_INVALID + line[2]);
                    }

                    try {
                        type = Type.valueOf(line[4]);
                    }catch (IllegalArgumentException e){
                        throw new IllegalArgumentException(TYPE_IS_INVALID + line[4]);
                    }

                    TransactionRecord record = new TransactionRecord(line[0], date, amount, line[3], type);
                    record.setRelatedTransaction(line[5]);
                    list.add(record);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        return null;
    }
}
