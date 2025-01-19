package edu.uoc.eduocation.model.school.location;

import edu.uoc.eduocation.model.school.SchoolException;

public class Location {
    private String address;
    private String city;
    private String country;
    private String phone;

    public Location(
            String address,
            String city,
            String country,
            String phone
    ) throws LocationException {
        setAddress(address);
        setCity(city);
        setCountry(country);
        setPhoneNumber(phone);
    }

    public void setAddress(String address) throws LocationException {
        if (address == null || address.isBlank())
            throw new LocationException(LocationException.INVALID_ADDRESS);
        else
            this.address = address.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setCity(String city) throws LocationException {
        if (city == null || city.isBlank())
            throw new LocationException(LocationException.INVALID_CITY);
        else
            this.city = city.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCountry(String country) throws LocationException {
        if (country == null || country.isBlank())
            throw new LocationException(LocationException.INVALID_COUNTRY);
        else
            this.country = country.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setPhoneNumber(String phone) throws LocationException {
        if (phone == null || phone.isBlank() || !phone.matches("^[0-9]{9}$"))
            throw new LocationException(LocationException.INVALID_PHONE);
        else
            this.phone = phone.trim();
    }

    public String getPhoneNumber() {
        return phone;
    }
}
