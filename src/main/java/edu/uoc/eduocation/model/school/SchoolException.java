package edu.uoc.eduocation.model.school;

public class SchoolException extends Exception {
    public static final String INVALID_NAME = "[ERROR] School name can't be null, empty or blank";

    /**
     * Exception thrown for errors related to school operations.
     * @param message the detail message.
     */
    public SchoolException(String message) {
        super(message);
    }
}
