package edu.uoc.eduocation.model.school;

import edu.uoc.eduocation.model.group.Group;

import java.util.LinkedList;

public class School {
    private String name;

    // Location class
    private LinkedList<Location> locations;
    private Integer numberOfLocations;

    // Group class
    private LinkedList<Group> groups;
    private Integer numberOfGroups;

    public School(
            String name,
            String[] locations
    ) {
        setName(name);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
