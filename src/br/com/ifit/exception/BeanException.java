package br.com.ifit.exception;

public class BeanException extends Exception {
    public BeanException(String message) {
        super(message);
    }

    public BeanException(String message, Throwable cause) {
        super(message, cause);
    }
}