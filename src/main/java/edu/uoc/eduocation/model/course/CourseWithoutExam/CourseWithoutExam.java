package edu.uoc.eduocation.model.course.CourseWithoutExam;

import edu.uoc.eduocation.model.course.Course;
import edu.uoc.eduocation.model.course.CourseException;

public class CourseWithoutExam extends Course {
    public CourseWithoutExam(
            String name,
            String code,
            Integer credits,
            Integer hours
    ) throws CourseException {
        super(name, code, credits, hours);
    }
}
