package edu.uoc.eduocation.model.course.CourseWithPracticeGroup;

import edu.uoc.eduocation.model.course.Course;
import edu.uoc.eduocation.model.course.CourseException;
import edu.uoc.eduocation.model.course.PracticeType;
import edu.uoc.eduocation.model.user.userTeacher.UserTeacher;

public class CourseWithPracticeGroup extends Course {
    private PracticeType practiceType;
    private int maximumStudents;

    /**
     * Constructs a CourseWithPracticeGroup with the specified details.
     * @param name the course name.
     * @param code the course code.
     * @param credits the number of credits.
     * @param hours the number of hours.
     * @param teacher the assigned teacher.
     * @param practiceType the type of practice.
     * @param maximumStudents the maximum number of students.
     * @throws CourseException if any validation fails.
     * @throws CourseWithPracticeGroupException if practice group validation fails.
     */
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
