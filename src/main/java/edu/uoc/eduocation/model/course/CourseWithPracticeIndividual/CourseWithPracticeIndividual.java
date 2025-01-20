package edu.uoc.eduocation.model.course.CourseWithPracticeIndividual;

import edu.uoc.eduocation.model.course.Course;
import edu.uoc.eduocation.model.course.CourseException;
import edu.uoc.eduocation.model.course.PracticeType;
import edu.uoc.eduocation.model.user.userTeacher.UserTeacher;

public class CourseWithPracticeIndividual extends Course {
    private PracticeType practiceType;

    public CourseWithPracticeIndividual(
            String name,
            String code,
            Integer credits,
            Integer hours,
            UserTeacher teacher,
            PracticeType practiceType
    ) throws CourseException {
        super(name, code, credits, hours, teacher);

        setPracticeType(practiceType);
    }

    public void setPracticeType(PracticeType practiceType) {
        this.practiceType = practiceType;
    }

    public PracticeType getPracticeType() {
        return practiceType;
    }
}
