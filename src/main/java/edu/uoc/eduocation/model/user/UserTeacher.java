package edu.uoc.eduocation.model.user;

import java.util.Date;

public class UserTeacher extends User {
    private String department;

    public UserTeacher(
            String nif,
            String name,
            String surname,
            Date birthdate,
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
