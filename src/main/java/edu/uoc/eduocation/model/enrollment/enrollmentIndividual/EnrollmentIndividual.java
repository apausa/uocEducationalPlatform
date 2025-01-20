package edu.uoc.eduocation.model.enrollment.enrollmentIndividual;

import edu.uoc.eduocation.model.course.Course;
import edu.uoc.eduocation.model.enrollment.Enrollment;
import edu.uoc.eduocation.model.enrollment.EnrollmentException;
import edu.uoc.eduocation.model.enrollment.EnrollmentStatus;

public class EnrollmentIndividual extends Enrollment {
    /**
     * Constructs an EnrollmentIndividual with the specified details.
     * @param semester the semester of the enrollment.
     * @param course the enrolled course.
     * @throws EnrollmentException if enrollment validation fails.
     */
    public EnrollmentIndividual(String semester, Course course) throws EnrollmentException {
        super(semester, course);
    }
}
