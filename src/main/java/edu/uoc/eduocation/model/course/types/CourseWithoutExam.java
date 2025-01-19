package edu.uoc.eduocation.model.course.types;

import edu.uoc.eduocation.model.course.Course;
import edu.uoc.eduocation.model.course.tCourseType;

public class CourseWithoutExam extends Course {
    public CourseWithoutExam(
            tCourseType type,
            String name,
            String code,
            Integer credits,
            Integer hours
    ) {
        super(type, name, code, credits, hours);
    }
}
