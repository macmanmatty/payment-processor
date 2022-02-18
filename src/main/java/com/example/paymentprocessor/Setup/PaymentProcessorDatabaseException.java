package com.example.paymentprocessor.Setup;

public class PaymentProcessorDatabaseException extends Exception {

    public PaymentProcessorDatabaseException() {
    }

    public PaymentProcessorDatabaseException(String message) {
        super(message);
    }

    public PaymentProcessorDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentProcessorDatabaseException(Throwable cause) {
        super(cause);
    }

    public PaymentProcessorDatabaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
