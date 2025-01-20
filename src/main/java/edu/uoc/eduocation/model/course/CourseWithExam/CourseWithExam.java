package edu.uoc.eduocation.model.course.CourseWithExam;

import edu.uoc.eduocation.model.course.Course;
import edu.uoc.eduocation.model.course.CourseException;
import edu.uoc.eduocation.model.user.userTeacher.UserTeacher;

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
            UserTeacher teacher,
            LocalDate date,
            LocalTime time,
            String location,
            String room
    ) throws CourseException, CourseWithExamException {
        super(name, code, credits, hours, teacher);

        setDate(date);
        setTime(time);
        setLocation(location);
        setRoom(room);
    }

    public void setDate(LocalDate date) throws CourseWithExamException {
        if (date == null)
            throw new CourseWithExamException(CourseWithExamException.INVALID_DATE);
        else
            this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setTime(LocalTime time) throws CourseWithExamException {
        if (time == null)
            throw new CourseWithExamException(CourseWithExamException.INVALID_TIME);
        else
            this.time = time;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setLocation(String location) throws CourseWithExamException {
        if (location == null || location.isBlank())
            throw new CourseWithExamException(CourseWithExamException.INVALID_LOCATION);
        else
            this.location = location.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setRoom(String room) throws CourseWithExamException {
        if (room == null || room.isBlank())
            throw new CourseWithExamException(CourseWithExamException.INVALID_ROOM);
        else
            this.room = room.trim();
    }

    public String getRoom() {
        return room;
    }
}
