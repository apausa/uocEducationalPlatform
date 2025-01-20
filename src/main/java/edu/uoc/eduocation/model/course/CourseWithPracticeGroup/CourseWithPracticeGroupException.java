package edu.uoc.eduocation.model.course.CourseWithPracticeGroup;

public class CourseWithPracticeGroupException extends Exception {
    public static final String INVALID_MAXIMUM_STUDENTS = "[ERROR] Group students can't be less than 0";

    /**
     * Exception thrown for errors related to courses with practice groups.
     * @param message the detail message.
     */
    public CourseWithPracticeGroupException(String message) {
        super(message);
    }
}
