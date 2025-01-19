package edu.uoc.eduocation.model.enrollment.types;

import edu.uoc.eduocation.model.course.Course;
import edu.uoc.eduocation.model.enrollment.Enrollment;
import edu.uoc.eduocation.model.enrollment.tEnrollmentStatus;

public class EnrollmentMultiple extends Enrollment {
    String[] groupNifs;

    public EnrollmentMultiple(
            String semester,
            Course course,
            String[] groupNifs
    ) {
        super(semester, course, tEnrollmentStatus.IN_PROGRESS);

        setGroupNifs(groupNifs);
    }

    public void setGroupNifs(String[] groupNifs) {
        this.groupNifs = groupNifs;
    }

    public String[] getGroupNifs() {
        return groupNifs;
    }
}
