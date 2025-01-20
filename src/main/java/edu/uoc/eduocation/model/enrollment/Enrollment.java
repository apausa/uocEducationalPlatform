package edu.uoc.eduocation.model.enrollment;

import com.google.gson.Gson;
import edu.uoc.eduocation.model.course.Course;

import java.util.HashMap;
import java.util.Map;

public abstract class Enrollment {
    private String semester;
    private EnrollmentStatus status;
    private Double mark = null;
    private Course course;

    /**
     * Constructs an Enrollment with the specified details.
     *
     * @param semester the semester of the enrollment.
     * @param course the enrolled course.
     * @param status the enrollment status.
     * @throws EnrollmentException if any validation fails.
     */
    public Enrollment(
            String semester,
            Course course,
            EnrollmentStatus status
    ) throws EnrollmentException {
        setSemester(semester);
        setCourse(course);
        setStatus(status);
    }

    public void setSemester(String semester) throws EnrollmentException {
        if (semester == null || semester.isEmpty())
            throw new EnrollmentException(EnrollmentException.INVALID_SEMESTER);
        else
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

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public Double getMark() {
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
