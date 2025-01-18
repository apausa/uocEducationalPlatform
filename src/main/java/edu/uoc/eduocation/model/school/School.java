package edu.uoc.eduocation.model.school;

public class School {
    private String name;
    private Location locations;
    private Integer numberOfLocations;
    private Integer numberOfGroups;

    public School(
            String name,
            Location locations
    ) {

    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setLocations(Location locations) {}
}
