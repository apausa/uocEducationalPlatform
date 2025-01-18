package edu.uoc.eduocation.model.school;

public class Location {
    private String address;
    private String city;
    private String country;
    private String phoneNumber;

    public Location(
            String address,
            String city,
            String country,
            String phoneNumber
    ) {
        setAddress(address);
        setCity(city);
        setCountry(country);
        setPhoneNumber(phoneNumber);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
