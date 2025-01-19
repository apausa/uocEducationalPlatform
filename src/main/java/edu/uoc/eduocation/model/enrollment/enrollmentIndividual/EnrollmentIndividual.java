package edu.uoc.eduocation.model.enrollment.enrollmentIndividual;

import edu.uoc.eduocation.model.course.Course;
import edu.uoc.eduocation.model.enrollment.Enrollment;
import edu.uoc.eduocation.model.enrollment.EnrollmentStatus;

public class EnrollmentIndividual extends Enrollment {
    public EnrollmentIndividual(String semester, Course course) {
        super(semester, course, EnrollmentStatus.PENDING);
    }
}
