package edu.uoc.eduocation.model.course.CourseWithPracticeIndividual;

import edu.uoc.eduocation.model.course.Course;
import edu.uoc.eduocation.model.course.CourseException;
import edu.uoc.eduocation.model.course.PracticeType;
import edu.uoc.eduocation.model.user.userTeacher.UserTeacher;

public class CourseWithPracticeIndividual extends Course {
    private PracticeType practiceType;

    /**
     * Constructs a CourseWithPracticeIndividual with the specified details.
     * @param name the course name.
     * @param code the course code.
     * @param credits the number of credits.
     * @param hours the number of hours.
     * @param teacher the assigned teacher.
     * @param practiceType the type of practice.
     * @throws CourseException if any validation fails.
     */
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
