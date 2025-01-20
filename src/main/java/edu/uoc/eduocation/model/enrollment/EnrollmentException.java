package edu.uoc.eduocation.model.enrollment;

public class EnrollmentException extends Exception {
    public static final String INVALID_SEMESTER = "[ERROR] Enrollment semester can't be null, empty or blank";

    public EnrollmentException(String message) {
        super(message);
    }
}
