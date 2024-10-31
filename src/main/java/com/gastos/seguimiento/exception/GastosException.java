package com.gastos.seguimiento.exception;

public class GastosException {

    public static class GastoNotFoundException extends RuntimeException {
        public GastoNotFoundException(String message) {
            super(message);
        }
    }

    public static class InvalidDateRangeException extends RuntimeException {
        public InvalidDateRangeException(String message) {
            super(message);
        }
    }

    public static class InvalidGastoDataException extends RuntimeException {
        public InvalidGastoDataException(String message) {
            super(message);
        }
    }
}
