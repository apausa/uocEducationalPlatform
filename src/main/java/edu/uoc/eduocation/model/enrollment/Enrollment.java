package edu.uoc.eduocation.model.enrollment;

import edu.uoc.eduocation.model.course.Course;

public abstract class Enrollment {
    private String semester;
    private tEnrollmentStatus status;
    private double mark;
    private Course course;

    public Enrollment(
            String semester,
            Course course,
            tEnrollmentStatus status

    ) {
        setSemester(semester);
        setCourse(course);
        setStatus(status);
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSemester() {
        return semester;
    }

    public void setStatus(tEnrollmentStatus status) {
        this.status = status;
    }

    public tEnrollmentStatus getStatus() {
        return status;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public double getMark() {
        return mark;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }
}
