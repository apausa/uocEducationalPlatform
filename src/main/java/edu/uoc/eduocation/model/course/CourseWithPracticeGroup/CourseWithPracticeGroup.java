package edu.uoc.eduocation.model.course.CourseWithPracticeGroup;

import edu.uoc.eduocation.model.course.Course;
import edu.uoc.eduocation.model.course.CourseException;
import edu.uoc.eduocation.model.course.PracticeType;
import edu.uoc.eduocation.model.user.userTeacher.UserTeacher;

public class CourseWithPracticeGroup extends Course {
    private PracticeType practiceType;
    private int maximumStudents;

    public CourseWithPracticeGroup(
            String name,
            String code,
            Integer credits,
            Integer hours,
            UserTeacher teacher,
            PracticeType practiceType,
            int maximumStudents
    ) throws CourseException, CourseWithPracticeGroupException {
        super(name, code, credits, hours, teacher);

        setPracticeType(practiceType);
        setMaximumStudents(maximumStudents);
    }

    public void setPracticeType(PracticeType practiceType) {
        this.practiceType = practiceType;
    }

    public PracticeType getPracticeType() {
        return practiceType;
    }

    public void setMaximumStudents(int maximumStudents) throws CourseWithPracticeGroupException {
        if (maximumStudents < 0)
            throw new CourseWithPracticeGroupException(CourseWithPracticeGroupException.INVALID_MAXIMUM_STUDENTS);
        else
            this.maximumStudents = maximumStudents;
    }

    public int getMaximumStudents() {
        return maximumStudents;
    }
}
