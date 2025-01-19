package edu.uoc.eduocation.model.school;

import edu.uoc.eduocation.model.group.Group;

import java.util.LinkedList;

public class School {
    private String name;
    private LinkedList<Location> locations;
    private LinkedList<Group> groups;

    public School(
            String name,
            LinkedList<Location> locations
    ) {
        setName(name);
        setLocations(locations);

        this.groups = new LinkedList<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setLocations(LinkedList<Location> locations){
        this.locations = locations;
    }

    public LinkedList<Location> getLocations(){
        return locations;
    }

    public void addGroup(Group group){
        this.groups.add(group);
    }

    public LinkedList<Group> getGroups(){
        return groups;
    }
}

