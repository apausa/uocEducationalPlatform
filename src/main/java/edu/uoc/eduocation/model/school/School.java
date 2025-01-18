package edu.uoc.eduocation.model.school;

import java.util.LinkedList;

public class School {
    private String name;
    private LinkedList<Location> locations;
    private Integer numberOfLocations;
    private Integer numberOfGroups;

    public School(
            String name,
            String[] locations
    ) {
        setName(name);

        this.locations = new LinkedList<>();

        for (String location: locations) {
            // TODO CONTROL ERRORS
            String[] attributes = location.split(",");
            setLocation(attributes[0], attributes[1], attributes[2], attributes[3]);
        }
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setLocation(
            String address,
            String city,
            String country,
            String phoneNumber
    ) {
        locations.add(new Location(address, city, country, phoneNumber));
    }
}
