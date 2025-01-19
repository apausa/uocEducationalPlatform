package edu.uoc.eduocation.model.enrollment;

import com.google.gson.Gson;
import edu.uoc.eduocation.model.course.Course;

import java.util.HashMap;
import java.util.Map;

public abstract class Enrollment {
    private String semester;
    private EnrollmentStatus status;
    private double mark;
    private Course course;

    public Enrollment(
            String semester,
            Course course,
            EnrollmentStatus status

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

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public double getMark() {
        return mark;
    }

    // Course class

    public void setCourse(Course course) {
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    // Override

    @Override
    public String toString() {
        Gson gson = new Gson();
        Map<String, Object> data = new HashMap<>();

        data.put("course", getCourse().getName());
        data.put("semester", getSemester());
        data.put("status", getStatus());
        data.put("mark", getMark());

        return gson.toJson(data);
    }
}
