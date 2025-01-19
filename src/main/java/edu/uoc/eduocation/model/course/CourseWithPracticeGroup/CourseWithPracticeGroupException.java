package edu.uoc.eduocation.model.course.CourseWithPracticeGroup;

public class CourseWithPracticeGroupException extends Exception {
    public static final String INVALID_MAXIMUM_STUDENTS = "[ERROR] Group students can't be less than 0";

    public CourseWithPracticeGroupException(String message) {
        super(message);
    }
}
