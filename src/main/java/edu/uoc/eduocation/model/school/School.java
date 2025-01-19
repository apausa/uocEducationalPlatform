package edu.uoc.eduocation.model.school;

import com.google.gson.Gson;
import edu.uoc.eduocation.model.group.Group;
import edu.uoc.eduocation.model.school.location.Location;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class School {
    private String name;
    private LinkedList<Location> locations;
    private LinkedList<Group> groups = new LinkedList<>();

    public School(
            String name,
            LinkedList<Location> locations
    ) throws SchoolException {
        setName(name);
        setLocations(locations);
    }

    public void setName(String name) throws SchoolException {
        if (name == null || name.isBlank())
            throw new SchoolException(SchoolException.INVALID_NAME);
        else
            this.name = name.trim();
    }

    public String getName(){
        return name;
    }

    // Location class

    public void setLocations(LinkedList<Location> locations){
        this.locations = locations;
    }

    public LinkedList<Location> getLocations(){
        return locations;
    }

    // Group class

    public void addGroup(Group group){
        this.groups.add(group);
    }

    public LinkedList<Group> getGroups(){
        return groups;
    }

    // Override

    @Override
    public String toString() {
        Gson gson = new Gson();
        Map<String, Object> data = new HashMap<>();

        data.put("name", getName());
        data.put("numberOfLocations", getLocations().size());
        data.put("numberOfGroups", getGroups().size());

        return gson.toJson(data);
    }
}
