# transaction-analyser111
transaction-analyser111

There are 2 ways to run the Transaction Analyser:
1. Using Main Class.
2. Using any valid unit Tests with valid parameters.

Transaction Analyser takes 4 parameters:
1. CSV file name. Place your csv file in resources folder. example: valid_csv_1.csv
2. Date parameters :  From Date and To Date. Transaction Analyser will fetch all transactions which lies in middle of these 2 dates,
    The format shall be dd/MM/YYYY hh:mm:ss, Example : 20/08/2018 12:50:02. Otherwise it throws an exception.
3. Merchant Name. Example : Kwik-E-Mart

Please check class com.transactionanalyser.main.Main or com.transactionanalyser.api.TransactionAnalyserTest.java for accurate details.

Illustrations of various IllegalArgumentException codes:
1. DATE_IS_INVALID : This code will appear when the Date is not in proper format. Either from CSV file or from any of the date input parameter mentioned above.
    It shall be in this format : dd/MM/YYYY hh:mm:ss , example : 20/08/2018 12:50:02
2. AMOUNT_IS_INVALID : This code is thrown when the amount is not valid double value in CSV under Amount Column.
3. TYPE_IS_INVALID : This code is thrown when the payment type is neither PAYMENT nor REVERSAL in CSV under column TYPE.
4. FILE_IS_INVALID : This code is thrown when file name is invalid or file does not exist.
5. MERCHANT_NAME_INVALID : This code is thrown when value of parameter merchant is not found in the content of CSV.
6. NO_TRANSACTION_FOUND : This is the output string thrown when none of the trnsaction matches the criteria by the given parameters.
7. REVERSAL_ID_INVALID : This code is thrown when any of the transaction with type REVERSAL have a related transaction which does not map to any real transaction.
