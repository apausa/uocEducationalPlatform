package edu.uoc.eduocation.model.enrollment;

public class EnrollmentException extends Exception {
    public static final String INVALID_SEMESTER = "[ERROR] Enrollment semester can't be null, empty or blank";

    /**
     * Exception thrown for errors related to enrollment operations.
     * @param message the detail message.
     */
    public EnrollmentException(String message) {
        super(message);
    }
}
