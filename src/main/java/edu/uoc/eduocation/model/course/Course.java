package edu.uoc.eduocation.model.course;

public abstract class Course {
    private tCourseType type;
    private String name;
    private String code;
    private Integer credits;
    private Integer hours;

    public Course(
            tCourseType type,
            String name,
            String code,
            Integer credits,
            Integer hours
    ) {
        setType(type);
        setName(name);
        setCode(code);
        setCredits(credits);
        setHours(hours);
    }

    public void setType(tCourseType type) {
        this.type = type;
    }

    public tCourseType getType() {
        return type;
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
