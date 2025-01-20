package edu.uoc.eduocation.model.course.CourseWithoutExam;

import edu.uoc.eduocation.model.course.Course;
import edu.uoc.eduocation.model.course.CourseException;
import edu.uoc.eduocation.model.user.userTeacher.UserTeacher;

public class CourseWithoutExam extends Course {
    /**
     * Constructs a CourseWithoutExam with the specified details.
     * @param name the course name.
     * @param code the course code.
     * @param credits the number of credits.
     * @param hours the number of hours.
     * @param teacher the assigned teacher.
     * @throws CourseException if any validation fails.
     */
    public CourseWithoutExam(
            String name,
            String code,
            Integer credits,
            Integer hours,
            UserTeacher teacher
    ) throws CourseException {
        super(name, code, credits, hours, teacher);
    }
}
