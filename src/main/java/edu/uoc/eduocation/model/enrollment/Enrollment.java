package edu.uoc.eduocation.model.enrollment;

import edu.uoc.eduocation.model.course.Course;
import edu.uoc.eduocation.model.user.UserStudent;

public class Enrollment {
    private String semester;
    private tStatus status;
    private double mark;

    // Student class
    private UserStudent student;

    // Course class
    private Course course;

    public Enrollment(
            String studentNIF,
            String courseCode,
            String semester,
            String enrollmentType,
            String additionalInfo
    ) {

    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSemester() {
        return semester;
    }

    public void setStatus(tStatus status) {
        this.status = status;
    }

    public tStatus getStatus() {
        return status;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public double getMark() {
        return mark;
    }
}
