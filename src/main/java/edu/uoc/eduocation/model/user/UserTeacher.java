package edu.uoc.eduocation.model.user;

import edu.uoc.eduocation.model.course.Course;
import edu.uoc.eduocation.model.group.Group;

import java.time.LocalDate;
import java.util.LinkedList;

public class UserTeacher extends User {
    private String department;

    // Group class
    private LinkedList<Group> groups;

    // Course class
    private Course course;

    public UserTeacher(
            String nif,
            String name,
            String surname,
            LocalDate birthdate,
            String department
    ) {
        super(nif, name, surname, birthdate);

        setDepartment(department);
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
}
