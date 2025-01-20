package edu.uoc.eduocation.model.group;

import com.google.gson.Gson;
import edu.uoc.eduocation.model.user.userStudent.UserStudent;
import edu.uoc.eduocation.model.user.userTeacher.UserTeacher;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Group {
    private String name;

    private UserTeacher tutor;
    private LinkedList<UserStudent> students;

    public Group(
            String name,
            UserTeacher tutor,
            LinkedList<UserStudent> students
            ) throws GroupException {
        setName(name);
        setTutor(tutor);
        setStudents(students);
    }

    public void setName(String name) throws GroupException {
        if (name == null || name.isBlank())
            throw new GroupException(GroupException.INVALID_NAME);
        else
            this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    // Tutor class

    public void setTutor(UserTeacher tutor) {
        this.tutor = tutor;
    }

    public UserTeacher getTutor() {
        return tutor;
    }

    // Students class

    public void setStudents(LinkedList<UserStudent> students) {
        this.students = students;
    }

    public LinkedList<UserStudent> getStudents() {
        return students;
    }

    // Override

    @Override
    public String toString() {
        Gson gson = new Gson();
        Map<String, Object> data = new HashMap<>();

        data.put("name", getName());
        data.put("tutor", String.format("%s %s", tutor.getName(), tutor.getSurname()));
        data.put("studentsCount", getStudents().size());

        return gson.toJson(data);
    }
}

