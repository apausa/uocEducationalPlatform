package edu.uoc.eduocation.model.enrollment.enrollmentMultiple;

import edu.uoc.eduocation.model.course.Course;
import edu.uoc.eduocation.model.enrollment.Enrollment;
import edu.uoc.eduocation.model.enrollment.EnrollmentException;
import edu.uoc.eduocation.model.enrollment.EnrollmentStatus;
import edu.uoc.eduocation.model.user.UserException;

public class EnrollmentMultiple extends Enrollment {
    String[] nifs;

    /**
     * Constructs an EnrollmentMultiple with the specified details.
     * @param semester the semester of the enrollment.
     * @param course the enrolled course.
     * @param nifs the array of student NIFs.
     * @throws UserException if user-related validation fails.
     * @throws EnrollmentException if enrollment validation fails.
     */
    public EnrollmentMultiple(
            String semester,
            Course course,
            String[] nifs
    ) throws UserException, EnrollmentException {
        super(semester, course);

        setGroupNifs(nifs);
    }

    public void setGroupNifs(String[] nifs) throws UserException {
        for (String nif : nifs) {
            if (!nif.matches("^[0-9]{8}[A-Za-z]$"))
                throw new UserException(UserException.INVALID_NIF);
            else
                this.nifs = nifs;
        }
    }

    public String[] getGroupNifs() {
        return nifs;
    }
}
