package com.msbank.sale.core.error.exception;

public class BaseException extends RuntimeException {

    private final transient Object[] parameters;

    public BaseException(final Object... parameters) {
        super();
        this.parameters = parameters;
    }

    public Object[] getParameters() {
        return parameters.clone();
    }
}
