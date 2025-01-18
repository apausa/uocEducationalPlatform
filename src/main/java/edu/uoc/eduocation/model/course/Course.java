package edu.uoc.eduocation.model.course;

import edu.uoc.eduocation.model.user.UserStudent;
import edu.uoc.eduocation.model.user.UserTeacher;

import java.util.LinkedList;

public class Course {
    private String name;
    private String code;
    private Integer credits;
    private Integer hours;

    // Teacher class
    private UserTeacher teacher;

    // Student class
    private LinkedList<UserStudent> students;

    public Course(
            String type,
            String name,
            String code,
            Integer credits,
            Integer hours,
            String teacherNif,
            String additionalInfo
    ) {
        setName(name);
        setCode(code);
        setCredits(credits);
        setHours(hours);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getHours() {
        return hours;
    }
}
