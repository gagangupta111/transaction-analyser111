package com.transactionanalyser.constants;

public final class ValidatorErrorCodes {

    public static final String DATE_IS_INVALID = " The value of Date is not as per accepted format ";
    public static final String AMOUNT_IS_INVALID = "The value of Amount is not valid ";
    public static final String TYPE_IS_INVALID = "The value of Type is not valid ";
    public static final String FILE_IS_INVALID = "The file path or name is invalid ";
    public static final String MERCHANT_NAME_INVALID = "Merchant name is either empty or not found in records.";
    public static final String NO_TRANSACTION_FOUND = "No transaction found which match with input parameters.";
    public static final String REVERSAL_ID_INVALID = "One of the Reversal Id is not an actual Payment ID";

}
