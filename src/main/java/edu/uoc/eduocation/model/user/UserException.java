package edu.uoc.eduocation.model.user;

public class UserException extends Exception {
    public static final String INVALID_NIF = "[ERROR] User NIF must have 8 numeric characters and 1 letter";
    public static final String INVALID_NAME = "[ERROR] User name can't be null, empty or blank";
    public static final String INVALID_SURNAME = "[ERROR] User surname can't be null, empty or blank";
    public static final String INVALID_DATE = "[ERROR] User birthdate can't be null or future";

    public UserException(String message) {
        super(message);
    }
}
