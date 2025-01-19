package edu.uoc.eduocation.model.course.CourseWithExam;

import edu.uoc.eduocation.model.course.Course;
import edu.uoc.eduocation.model.course.CourseException;
import edu.uoc.eduocation.model.user.UserException;

import java.time.LocalDate;
import java.time.LocalTime;

public class CourseWithExam extends Course {
    private LocalDate date;
    private LocalTime time;
    private String location;
    private String room;

    public CourseWithExam(
            String name,
            String code,
            Integer credits,
            Integer hours,
            LocalDate date,
            LocalTime time,
            String location,
            String room
    ) throws CourseException, CourseWithExamException {
        super(name, code, credits, hours);

        setDate(date);
        setTime(time);
        setLocation(location);
        setRoom(room);
    }

    public void setDate(LocalDate date) throws CourseWithExamException {
        if (date == null || date.isAfter(LocalDate.now()))
            throw new CourseWithExamException(CourseWithExamException.INVALID_DATE);
        else
            this.date = date;
    }

    public LocalDate getDate() throws CourseWithExamException {
        return date;
    }

    public void setTime(LocalTime time) throws CourseWithExamException {
        if (time == null || time.isAfter(LocalTime.now()))
            throw new CourseWithExamException(CourseWithExamException.INVALID_TIME);
        else
            this.time = time;
    }

    public LocalTime getTime() throws CourseWithExamException {
        return time;
    }

    public void setLocation(String location) throws CourseWithExamException {
        if (location == null || location.isBlank())
            throw new CourseWithExamException(CourseWithExamException.INVALID_LOCATION);
        else
            this.location = location.trim();
    }

    public String getLocation() throws CourseWithExamException {
        return location;
    }

    public void setRoom(String room) throws CourseWithExamException {
        if (room == null || room.isBlank())
            throw new CourseWithExamException(CourseWithExamException.INVALID_ROOM);
        else
            this.room = room.trim();
    }

    public String getRoom() throws CourseWithExamException {
        return room;
    }
}
