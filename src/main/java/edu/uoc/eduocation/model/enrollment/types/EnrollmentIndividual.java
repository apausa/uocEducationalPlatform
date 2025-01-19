package edu.uoc.eduocation.model.enrollment.types;

import edu.uoc.eduocation.model.course.Course;
import edu.uoc.eduocation.model.enrollment.Enrollment;
import edu.uoc.eduocation.model.enrollment.tEnrollmentStatus;

public class EnrollmentIndividual extends Enrollment {
    public EnrollmentIndividual(String semester, Course course) {
        super(semester, course, tEnrollmentStatus.PENDING);
    }
}
