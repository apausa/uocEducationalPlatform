package edu.uoc.eduocation.model.user;

import java.util.Date;

public class UserStudent extends User {
    public UserStudent(
            String nif,
            String name,
            String surname,
            Date birthdate
    ) {
        super(nif, name, surname, birthdate);
    }
}
