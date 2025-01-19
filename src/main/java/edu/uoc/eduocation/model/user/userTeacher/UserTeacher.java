package edu.uoc.eduocation.model.user.userTeacher;

import edu.uoc.eduocation.model.user.User;
import edu.uoc.eduocation.model.user.UserException;

import java.time.LocalDate;

public class UserTeacher extends User {
    private String department;

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
