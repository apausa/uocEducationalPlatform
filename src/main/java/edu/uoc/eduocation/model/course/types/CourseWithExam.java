package edu.uoc.eduocation.model.course.types;

import edu.uoc.eduocation.model.course.Course;
import edu.uoc.eduocation.model.course.tCourseType;

import java.time.LocalDate;
import java.time.LocalTime;

public class CourseWithExam extends Course {
    private LocalDate date;
    private LocalTime time;
    private String location;
    private String room;

    public CourseWithExam(
            tCourseType type,
            String name,
            String code,
            Integer credits,
            Integer hours,
            LocalDate date,
            LocalTime time,
            String location,
            String room
    ) {
        super(type, name, code, credits, hours);

        setDate(date);
        setTime(time);
        setLocation(location);
        setRoom(room);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRoom() {
        return room;
    }
}
