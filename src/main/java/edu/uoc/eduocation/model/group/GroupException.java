package edu.uoc.eduocation.model.group;

public class GroupException extends Exception {
    public static final String INVALID_NAME = "[ERROR] Group name can't be null, empty or blank";

    /**
     * Exception thrown for errors related to group operations.
     * @param message the detail message.
     */
    public GroupException(String message) {
        super(message);
    }
}
