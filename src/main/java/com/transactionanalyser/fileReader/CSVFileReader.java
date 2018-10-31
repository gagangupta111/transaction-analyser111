package com.transactionanalyser.fileReader;

import com.opencsv.CSVReader;
import com.transactionanalyser.model.TransactionRecord;
import com.transactionanalyser.model.Type;
import sun.misc.ClassLoaderUtil;

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
    public static final String AMOUNT_IS_INVALID = "The value of Amount is not valid ";
    public static final String TYPE_IS_INVALID = "The value of Type is not valid ";
    public static final String FILE_IS_INVALID = "The file path or name is invalid ";

    public CSVFileReader(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    @Override
    public List<TransactionRecord> read() {

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
                        throw new IllegalArgumentException(CSV_DATE_IS_INVALID + formatter + " " + line[1]);
                    }

                    try {
                        amount = Double.parseDouble(line[2].trim());
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException(AMOUNT_IS_INVALID + line[2]);
                    }

                    try {
                        type = Type.valueOf(line[4].trim());
                    }catch (IllegalArgumentException e){
                        throw new IllegalArgumentException(TYPE_IS_INVALID + line[4]);
                    }

                    TransactionRecord record = new TransactionRecord(line[0].trim(), date, amount, line[3].trim(), type, line[5].trim());
                    list.add(record);

                }

            } catch (IOException e) {
                throw new IllegalArgumentException(FILE_IS_INVALID + csvFilePath);
            }

        return list;
    }
}
