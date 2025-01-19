package edu.uoc.eduocation.model.group;

import edu.uoc.eduocation.model.user.types.UserStudent;
import edu.uoc.eduocation.model.user.types.UserTeacher;

import java.util.LinkedList;

public class Group {
    private String name;
    private UserTeacher tutor;
    private LinkedList<UserStudent> students;

    public Group(
            String name,
            UserTeacher tutor,
            LinkedList<UserStudent> students
            ) {
        setName(name);
        setTutor(tutor);
        setStudents(students);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTutor(UserTeacher tutor) {
        this.tutor = tutor;
    }

    public UserTeacher getTutor() {
        return tutor;
    }

    public void setStudents(LinkedList<UserStudent> students) {
        this.students = students;
    }

    public LinkedList<UserStudent> getStudents() {
        return students;
    }
}

