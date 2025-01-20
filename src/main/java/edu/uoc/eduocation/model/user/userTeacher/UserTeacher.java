package edu.uoc.eduocation.model.user.userTeacher;

import edu.uoc.eduocation.model.user.User;
import edu.uoc.eduocation.model.user.UserException;

import java.time.LocalDate;

public class UserTeacher extends User {
    private String department;

    /**
     * Constructs a UserTeacher with the specified details.
     *
     * @param nif the NIF of the teacher.
     * @param name the name of the teacher.
     * @param surname the surname of the teacher.
     * @param birthdate the birthdate of the teacher.
     * @param department the department of the teacher.
     * @throws UserTeacherException if teacher-specific validation fails.
     * @throws UserException if user validation fails.
     */
    public UserTeacher(
            String nif,
            String name,
            String surname,
            LocalDate birthdate,
            String department
    ) throws UserTeacherException, UserException {
        super(nif, name, surname, birthdate);

        setDepartment(department);
    }

    public void setDepartment(String department) throws UserTeacherException {
        if (department == null || department.isBlank())
            throw new UserTeacherException(UserTeacherException.INVALID_DEPARTMENT);
        else
            this.department = department.trim();
    }

    public String getDepartment() {
        return department;
    }
}
