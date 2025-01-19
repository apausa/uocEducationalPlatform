package edu.uoc.eduocation.model.group;

public class GroupException extends Exception {
    public static final String INVALID_NAME = "[ERROR] Group name can't be null, empty or blank";

    public GroupException(String message) {
        super(message);
    }
}
