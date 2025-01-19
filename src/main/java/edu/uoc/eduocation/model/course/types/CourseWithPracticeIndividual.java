package edu.uoc.eduocation.model.course.types;

import edu.uoc.eduocation.model.course.Course;
import edu.uoc.eduocation.model.course.tCourseType;

public class CourseWithPracticeIndividual extends Course {
    private tPracticeType practiceType;

    public CourseWithPracticeIndividual(
            tCourseType type,
            String name,
            String code,
            Integer credits,
            Integer hours,
            tPracticeType practiceType
    ) {
        super(type, name, code, credits, hours);
        setPracticeType(practiceType);
    }

    public void setPracticeType(tPracticeType practiceType) {
        this.practiceType = practiceType;
    }

    public tPracticeType getPracticeType() {
        return practiceType;
    }
}
