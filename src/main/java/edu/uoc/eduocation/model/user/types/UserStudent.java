package edu.uoc.eduocation.model.user.types;

import edu.uoc.eduocation.model.enrollment.Enrollment;
import edu.uoc.eduocation.model.user.User;

import java.time.LocalDate;
import java.util.LinkedList;

public class UserStudent extends User {
    public LinkedList<Enrollment> enrollments = new LinkedList<>();

    public UserStudent(
            String nif,
            String name,
            String surname,
            LocalDate birthdate
    ) {
        super(nif, name, surname, birthdate);
    }

    // Enrollment class

    public void addEnrollment(Enrollment enrollment) {
        this.enrollments.add(enrollment);
    }

    public LinkedList<Enrollment> getEnrollments() {
        return enrollments;
    }
}
