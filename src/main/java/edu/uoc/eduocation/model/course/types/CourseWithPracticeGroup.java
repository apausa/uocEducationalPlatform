package edu.uoc.eduocation.model.course.types;

import edu.uoc.eduocation.model.course.Course;
import edu.uoc.eduocation.model.course.tCourseType;

public class CourseWithPracticeGroup extends Course {
    private tPracticeType practiceType;
    private int maximumStudents;

    public CourseWithPracticeGroup(
            tCourseType type,
            String name,
            String code,
            Integer credits,
            Integer hours,
            tPracticeType practiceType,
            int maximumStudents
    ) {
        super(type, name, code, credits, hours);

        setPracticeType(practiceType);
        setMaximumStudents(maximumStudents);
    }

    public void setPracticeType(tPracticeType practiceType) {
        this.practiceType = practiceType;
    }

    public tPracticeType getPracticeType() {
        return practiceType;
    }

    public void setMaximumStudents(int maximumStudents) {
        this.maximumStudents = maximumStudents;
    }

    public int getMaximumStudents() {
        return maximumStudents;
    }
}
