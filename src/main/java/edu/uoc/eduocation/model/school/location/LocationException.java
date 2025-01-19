package edu.uoc.eduocation.model.school.location;

public class LocationException extends Exception {
    public static final String INVALID_ADDRESS = "[ERROR] School address can't be null, empty or blank";
    public static final String INVALID_CITY = "[ERROR] School city can't be null, empty or blank";
    public static final String INVALID_COUNTRY = "[ERROR] School country can't be null, empty or blank";
    public static final String INVALID_PHONE = "[ERROR] School phone must have 9 numeric characters";

    public LocationException(String message) {
        super(message);
    }
}
