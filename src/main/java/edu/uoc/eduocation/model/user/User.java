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
    ) throws UserException {
        setNif(nif);
        setName(name);
        setSurname(surname);
        setBirthdate(birthdate);
    }

    public void setNif(String nif) throws UserException {
        if (nif == null || nif.isBlank() || !nif.matches("^[0-9]{8}[A-Za-z]$"))
            throw new UserException(UserException.INVALID_NIF);
        else
            this.nif = nif.trim();
    }

    public String getNif() {
        return nif;
    }

    public void setName(String name) throws UserException {
        if (name == null || name.isBlank())
            throw new UserException(UserException.INVALID_NAME);
        else
            this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) throws UserException {
        if (surname == null || surname.isBlank())
            throw new UserException(UserException.INVALID_SURNAME);
        else
            this.surname = surname.trim();
    }

    public String getSurname() {
        return surname;
    }

    public void setBirthdate(LocalDate birthdate) throws UserException {
        if (birthdate == null || birthdate.isAfter(LocalDate.now()))
            throw new UserException(UserException.INVALID_DATE);
        else
            this.birthdate = birthdate;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        Map<String, Object> data = new HashMap<>();

        data.put("nif", getNif());
        data.put("name", getName());
        data.put("surname", getSurname());
        data.put("birthdate", getBirthdate().toString());

        return gson.toJson(data);
    }
}
