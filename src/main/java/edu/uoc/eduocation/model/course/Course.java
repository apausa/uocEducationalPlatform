package edu.uoc.eduocation.model.course;

import com.google.gson.Gson;
import edu.uoc.eduocation.model.user.userTeacher.UserTeacher;

import java.util.HashMap;
import java.util.Map;

/**
 * Course abstract class
 */
public abstract class Course {
    private String name;
    private String code;
    private Integer credits;
    private Integer hours;

    private UserTeacher teacher;

    /**
     * Constructs a Course with the specified details.
     *
     * @param name the course name.
     * @param code the course code.
     * @param credits the number of credits.
     * @param hours the number of hours.
     * @param teacher the assigned teacher.
     * @throws CourseException if any validation fails.
     */
    public Course(
            String name,
            String code,
            Integer credits,
            Integer hours,
            UserTeacher teacher
    ) throws CourseException {
        setName(name);
        setCode(code);
        setCredits(credits);
        setHours(hours);
        setTeacher(teacher);
    }

    public void setName(String name) throws CourseException {
        if (name == null || name.isBlank())
            throw new CourseException(CourseException.INVALID_NAME);
        else
            this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) throws CourseException {
        if (code == null || code.isBlank())
            throw new CourseException(CourseException.INVALID_CODE);
        else
            this.code = code.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCredits(int credits) throws CourseException {
        if (credits < 0)
            throw new CourseException(CourseException.INVALID_CREDITS);
        else
            this.credits = credits;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setHours(Integer hours) throws CourseException {
        if (hours < 0)
            throw new CourseException(CourseException.INVALID_HOURS);
        else
            this.hours = hours;
    }

    public Integer getHours() {
        return hours;
    }

    // Teacher class

    public void setTeacher(UserTeacher teacher) {
        this.teacher = teacher;
    }

    public UserTeacher getTeacher() {
        return teacher;
    }

    // Override toString

    @Override
    public String toString() {
        Gson gson = new Gson();
        Map<String, Object> data = new HashMap<>();

        data.put("name", getName());
        data.put("code", getCode());
        data.put("credits", getCredits());
        data.put("hours", getHours());

        return gson.toJson(data);
    }
}
