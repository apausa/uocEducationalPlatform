package edu.uoc.eduocation.model.user.userStudent;

import edu.uoc.eduocation.model.enrollment.Enrollment;
import edu.uoc.eduocation.model.user.User;
import edu.uoc.eduocation.model.user.UserException;

import java.time.LocalDate;
import java.util.LinkedList;

public class UserStudent extends User {
    public LinkedList<Enrollment> enrollments = new LinkedList<>();

    /**
     * Constructs a UserStudent with the specified details.
     *
     * @param nif the NIF of the student.
     * @param name the name of the student.
     * @param surname the surname of the student.
     * @param birthdate the birthdate of the student.
     * @throws UserException if user validation fails.
     */
    public UserStudent(
            String nif,
            String name,
            String surname,
            LocalDate birthdate
    ) throws UserException {
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
