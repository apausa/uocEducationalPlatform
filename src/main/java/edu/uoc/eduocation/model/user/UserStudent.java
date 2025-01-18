package edu.uoc.eduocation.model.user;

import edu.uoc.eduocation.model.enrollment.Enrollment;
import edu.uoc.eduocation.model.group.Group;

import java.time.LocalDate;
import java.util.LinkedList;

public class UserStudent extends User {
    // Group class
    private Group group;

    // Enrollment class
    private LinkedList<Enrollment> enrollments;

    public UserStudent(
            String nif,
            String name,
            String surname,
            LocalDate birthdate
    ) {
        super(nif, name, surname, birthdate);
    }
}
