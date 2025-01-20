package edu.uoc.eduocation.model.course;

public class CourseException extends Exception {
    public static final String INVALID_NAME = "[ERROR] Course name can't be null, empty or blank";
    public static final String INVALID_CODE = "[ERROR] Course code can't be null, empty or blank";
    public static final String INVALID_CREDITS = "[ERROR] Course credits can't be less than 0";
    public static final String INVALID_HOURS = "[ERROR] Course hours can't be less than 0";

    /**
     * Exception thrown for course-related errors.
     * @param message the detail message.
     */
    public CourseException(String message) {
        super(message);
    }
}
