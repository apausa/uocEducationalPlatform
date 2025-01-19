package edu.uoc.eduocation.model.user.types;

import edu.uoc.eduocation.model.user.User;

import java.time.LocalDate;

public class UserTeacher extends User {
    private String department;

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
