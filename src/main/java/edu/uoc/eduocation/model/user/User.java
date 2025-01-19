package edu.uoc.eduocation.model.user;

import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    public String toString() {
        Gson gson = new Gson();
        Map<String, Object> data = new HashMap<>();

        data.put("NIF", getNif());
        data.put("Name", getName());
        data.put("Surname", getSurname());
        data.put("Birthdate", getBirthdate().toString());

        return gson.toJson(data);
    }
}
