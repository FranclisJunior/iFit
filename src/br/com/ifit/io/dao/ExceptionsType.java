package br.com.ifit.io.dao;

import java.util.ResourceBundle;

public class ExceptionsType {

    private static final String EXCEPTIONS_FILE = "settings";
    private static ResourceBundle resourceBundle;
    public static final String INSERT_ERROR = "MESSAGE_INSERT_ERROR";
    public static final String UPDATE_ERROR = "MESSAGE_UPDATE_ERROR";
    public static final String REMOVE_ERROR = "MESSAGE_REMOVE_ERROR";
    public static final String READ_ERROR = "MESSAGE_READ_ERROR";

    static {
        resourceBundle = ResourceBundle.getBundle(EXCEPTIONS_FILE);
    }

    public static String getMessage(String type) {
        return resourceBundle.getString(type);
    }
}