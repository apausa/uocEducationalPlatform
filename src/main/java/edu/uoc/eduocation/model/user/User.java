package edu.uoc.eduocation.model.user;

import java.time.LocalDate;

public abstract class User {
    private String nif;
    private String name;
    private String surname;
    private LocalDate birthdate;

    public User (
            String nif,
            String name,
            String surname,
            LocalDate birthdate
    ) {
        setNif(nif);
        setName(name);
        setSurname(surname);
        setBirthdate(birthdate);
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNif() {
        return nif;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }
}
