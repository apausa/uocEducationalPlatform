package edu.uoc.eduocation.model.group;

import edu.uoc.eduocation.model.school.School;
import edu.uoc.eduocation.model.user.UserStudent;
import edu.uoc.eduocation.model.user.UserTeacher;

import java.util.LinkedList;

public class Group {
    private String name;

    // School class
    private School school;

    // Teacher class
    private UserTeacher tutor;

    // Student class
    private LinkedList<UserStudent> students;
    private int numberOfStudents;

    public Group(
            String schoolName,
            String groupName,
            String tutorNIF,
            String[] studentData
            ) {
        setName(groupName);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
