package edu.uoc.eduocation.controller;

import edu.uoc.eduocation.model.school.School;

import java.io.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Controller class for the EdUOCation application
 */
public class EdUOCationController {
    private LinkedList<School> schools;

    /**
     * Constructor that loads the data from the files
     * @param schoolsFilename Name of the file with the schools data
     * @param teachersFilename Name of the file with the teachers data
     * @param courseFilename Name of the file with the courses data
     * @param studentGroupsFilename Name of the file with the student groups data
     * @param enrollmentsFilename Name of the file with the enrollments data
     */
    public EdUOCationController(
            String schoolsFilename,
            String teachersFilename,
            String courseFilename,
            String studentGroupsFilename,
            String enrollmentsFilename
    ) {
        schools = new LinkedList<>();

        loadSchools(schoolsFilename);
        loadTeachers(teachersFilename);
        loadCourses(courseFilename);
        loadStudentGroups(studentGroupsFilename);
        loadEnrollments(enrollmentsFilename);
    }

    /**
     * Load schools from a file
     * @param filename Name of the file to load
     */
    private void loadSchools(String filename) {
        try (InputStream is = getClass().getResourceAsStream("/data/" + filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Skip comments or empty lines
                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }

                // Split school name and locations
                String[] parts = line.split("\\|", 2);
                if (parts.length < 2) {
                    continue; // Skip malformed lines
                }

                String schoolName = parts[0].trim();
                String[] locationStrings = parts[1].split("\\|");

                addSchool(schoolName, locationStrings);
            }
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Load teachers from a file
     * @param filename Name of the file to load
     */
    private void loadTeachers(String filename) {
        try (InputStream is = getClass().getResourceAsStream("/data/" + filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Skip comments or empty lines
                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }

                // Split teacher data
                String[] parts = line.split("\\|");
                if (parts.length != 5) {
                    continue; // Skip malformed lines
                }

                String nif = parts[0].trim();
                String name = parts[1].trim();
                String surname = parts[2].trim();
                LocalDate birthdate = LocalDate.parse(parts[3].trim());
                String department = parts[4].trim();

                addTeacher(nif, name, surname, birthdate, department);
            }
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Load courses from a file
     * @param filename Name of the file to load
     */
    private void loadCourses(String filename) {
        try (InputStream is = getClass().getResourceAsStream("/data/" + filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Skip comments or empty lines
                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }

                // Split course data
                String[] parts = line.split("\\|");
                if (parts.length < 6) {
                    continue; // Skip malformed lines
                }

                String type = parts[0].trim();
                String name = parts[1].trim();
                String code = parts[2].trim();
                int credits = Integer.parseInt(parts[3].trim());
                int hours = Integer.parseInt(parts[4].trim());
                String teacherNif = parts[5].trim();
                String additionalInfo = parts.length > 6 ? parts[6].trim() : null;

                addCourse(type, name, code, credits, hours, teacherNif, additionalInfo);
            }
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Load student groups from a file
     * @param filename Name of the file to load
     */
    private void loadStudentGroups(String filename) {
        try (InputStream is = getClass().getResourceAsStream("/data/" + filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Skip comments or empty lines
                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }

                // Split school name, group name, tutor, and students
                String[] parts = line.split("\\|", 4);
                if (parts.length < 4) {
                    continue; // Skip malformed lines
                }

                String schoolName = parts[0].trim();
                String groupName = parts[1].trim();
                String tutorNif = parts[2].trim();
                String[] studentData = parts[3].split(",");

                addStudentGroup(schoolName, groupName, tutorNif, studentData);
            }
        } catch (Exception e) {
            System.err.println("Error reading student groups file: " + e.getMessage());
        }
    }

    /**
     * Load the enrollments from a file
     * @param filename Name of the file to load
     */
    private void loadEnrollments(String filename) {
        try (InputStream is = getClass().getResourceAsStream("/data/" + filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Skip comments or empty lines
                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }

                // Split enrollment data
                String[] parts = line.split("\\|", 5);
                if (parts.length < 5) {
                    continue; // Skip malformed lines
                }

                String studentNif = parts[0].trim();
                String courseCode = parts[1].trim();
                String semester = parts[2].trim();
                String enrollmentType = parts[3].trim();
                String additionalInfo = parts[4].trim();

                addEnrollment(studentNif, courseCode, semester, enrollmentType, additionalInfo);
            }
        } catch (Exception e) {
            System.err.println("Error reading enrollments file: " + e.getMessage());
        }
    }

    /**
     * Add a student group to the controller
     * @param name Name of the student group
     * @param locations Array of strings with the format "name, address, city, country"
     */
    public void addSchool(String name, String... locations) {
        // TODO
        schools.add(new School(name, locations));
    }

    /**
     * Add a student group to the controller
     * @param nif NIF of the teacher
     * @param name Name of the teacher
     * @param surname Surname of the teacher
     * @param birthdate Birthdate of the teacher
     * @param department Department of the teacher
     */
    public void addTeacher(String nif, String name, String surname, LocalDate birthdate, String department) {
        //TODO

    }

    /**
     * Add a student group to the controller
     * @param type Type of the course
     * @param name Name of the course
     * @param code Code of the course
     * @param credits Credits of the course
     * @param hours Hours of the course
     * @param teacherNif NIF of the teacher
     * @param additionalInfo Additional information for the course (exam or practice information)
     */
    public void addCourse(String type, String name, String code, int credits, int hours, String teacherNif, String additionalInfo) {
        //TODO
    }

    /**
     * Add a group of students to the university
     * @param schoolName Name of the school
     * @param groupName Name of the group
     * @param tutorNIF NIF of the tutor
     * @param studentData Array of the students data
     */
    public void addStudentGroup(String schoolName, String groupName, String tutorNIF, String[] studentData) {
        //TODO
    }

    /**
     * Add an enrollment to the student
     * @param studentNIF NIF of the student
     * @param courseCode Code of the course
     * @param semester Semester of the enrollment
     * @param enrollmentType Type of the enrollment (individual or multiple)
     * @param additionalInfo Additional information for the enrollment
     */
    public void addEnrollment(String studentNIF, String courseCode, String semester, String enrollmentType, String additionalInfo) {
        //TODO
    }

    public boolean updateEnrollmentMark(String course, String semester, String status, String studentNif, double mark) {
        //TODO
    }


    /**
     * Get the list of schools
     * @return List of schools
     */
    public List<String> getSchools() {
        //TODO
    }

    /**
     * Get the list of teachers
     * @return List of teachers
     */
    public List<String> getTeachers() {
        //TODO
    }

    /**
     * Get the list of courses
     * @return List of courses
     */
    public List<String> getCourses() {
        //TODO
    }

    /**
     * Get the list of groups for a school
     * @param schoolName Name of the school
     * @return List of groups
     */
    public List<String> getGroups(String schoolName) {
        //TODO
    }

    /**
     * Get the list of students for a group
     * @param schoolName Name of the school
     * @param groupName Name of the group
     * @return List of students
     */
    public List<String> getStudents(String schoolName, String groupName) {
        //TODO
    }

    /**
     * Get the list of enrollments for a student
     * @param schoolName Name of the school
     * @param groupName Name of the group
     * @param studentNIF NIF of the student
     * @return List of enrollments
     */
    public List<String> getEnrollments(String schoolName, String groupName, String studentNIF) {
        //TODO
    }

}
