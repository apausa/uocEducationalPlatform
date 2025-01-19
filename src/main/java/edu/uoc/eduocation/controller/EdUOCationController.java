package edu.uoc.eduocation.controller;

import edu.uoc.eduocation.model.course.Course;
import edu.uoc.eduocation.model.course.tCourseType;
import edu.uoc.eduocation.model.course.types.*;
import edu.uoc.eduocation.model.enrollment.Enrollment;
import edu.uoc.eduocation.model.enrollment.tEnrollmentStatus;
import edu.uoc.eduocation.model.enrollment.tEnrollmentType;
import edu.uoc.eduocation.model.enrollment.types.EnrollmentIndividual;
import edu.uoc.eduocation.model.enrollment.types.EnrollmentMultiple;
import edu.uoc.eduocation.model.school.Location;
import edu.uoc.eduocation.model.school.School;
import edu.uoc.eduocation.model.group.Group;
import edu.uoc.eduocation.model.user.types.UserStudent;
import edu.uoc.eduocation.model.user.types.UserTeacher;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static edu.uoc.eduocation.model.enrollment.tEnrollmentType.MULTIPLE;

/**
 * Controller class for the EdUOCation application
 */
public class EdUOCationController {
    private LinkedList<School> schools;
    private LinkedList<UserTeacher> teachers;
    private LinkedList<Course> courses;

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
        teachers = new LinkedList<>();
        courses = new LinkedList<>();

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
    public void addSchool(
            String name,
            String... locations
    ) {
        // Create locations
        LinkedList<Location> schoolLocations = new LinkedList<>();

        for (String location : locations) {
            String[] arguments = location.split(",");

            schoolLocations.add(new Location(
                    arguments[0], // address
                    arguments[1], // city
                    arguments[2], // country
                    arguments[3]  // phoneNumber
            ));
        }

        // Create school
        schools.add(new School(
                name,
                schoolLocations
        ));
    }

    /**
     * Add a student group to the controller
     * @param nif NIF of the teacher
     * @param name Name of the teacher
     * @param surname Surname of the teacher
     * @param birthdate Birthdate of the teacher
     * @param department Department of the teacher
     */
    public void addTeacher(
            String nif,
            String name,
            String surname,
            LocalDate birthdate,
            String department
    ) {
        // Create teacher
        teachers.add(new UserTeacher(
                nif,
                name,
                surname,
                birthdate,
                department
        ));
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
    public void addCourse(
            String type,
            String name,
            String code,
            int credits,
            int hours,
            String teacherNif,
            String additionalInfo
    ) {
        UserTeacher teacher = getTeacher(teacherNif);

        if (teacher == null)
            return;

        String[] arguments = additionalInfo.split(",");

        switch(tCourseType.valueOf(type)) {
            case CourseWithoutExam:
                courses.add(new CourseWithoutExam(
                        tCourseType.valueOf(type),
                        name,
                        code,
                        credits,
                        hours
                ));
                break;
            case CourseWithExam:
                courses.add(new CourseWithExam(
                        tCourseType.valueOf(type),
                        name,
                        code,
                        credits,
                        hours,
                        LocalDate.parse(arguments[0]), // Date
                        LocalTime.parse(arguments[1]), // Time
                        arguments[2], // Location
                        arguments[3]  // Room
                ));
                break;
            case CourseWithPracticeGroup:
                courses.add(new CourseWithPracticeGroup(
                        tCourseType.valueOf(type),
                        name,
                        code,
                        credits,
                        hours,
                        tPracticeType.valueOf(arguments[0]), // Practice type
                        Integer.parseInt(arguments[1]) // Maximum students
                ));
                break;
            case CourseWithPracticeIndividual:
                courses.add(new CourseWithPracticeIndividual(
                        tCourseType.valueOf(type),
                        name,
                        code,
                        credits,
                        hours,
                        tPracticeType.valueOf(arguments[0]) // Practice type
                ));
                break;
        }
    }

    /**
     * Add a group of students to the university
     * @param schoolName Name of the school
     * @param groupName Name of the group
     * @param tutorNIF NIF of the tutor
     * @param studentData Array of the students data
     */
    public void addStudentGroup(
            String schoolName,
            String groupName,
            String tutorNIF,
            String[] studentData
    ) {
        // Get teacher
        UserTeacher tutor = getTeacher(tutorNIF);

        if (tutor == null)
            return;

        // Create students
        LinkedList<UserStudent> students = new LinkedList<>();

        for (String student: studentData) {
            String[] arguments = student.split(":");
            students.add(new UserStudent(
                    arguments[0], // nif
                    arguments[1], // name
                    arguments[2], // surname
                    LocalDate.parse(arguments[3]) // birthdate
            ));
        }

        // Get school
        for (School currentSchool : schools) {
            if (currentSchool.getName().equals(schoolName)) {
                // Create and add group to school
                currentSchool.addGroup(new Group(
                        groupName,
                        tutor,
                        students
                ));
            }
        }
    }

    /**
     * Add an enrollment to the student
     * @param studentNIF NIF of the student
     * @param courseCode Code of the course
     * @param semester Semester of the enrollment
     * @param enrollmentType Type of the enrollment (individual or multiple)
     * @param additionalInfo Additional information for the enrollment
     */
    public void addEnrollment(
            String studentNIF,
            String courseCode,
            String semester,
            String enrollmentType,
            String additionalInfo
    ) {
        Course course = getCourse(courseCode);

        if (course == null)
            return;

        for (School school : schools) {
            for (Group group : school.getGroups()) {
                for (UserStudent student : group.getStudents()) {
                    if (student.getNif().equals(studentNIF) && tEnrollmentType.valueOf(enrollmentType) == MULTIPLE) {
                        String[] groupIds = additionalInfo.split(",");

                        student.addEnrollment(new EnrollmentMultiple(
                                semester,
                                course,
                                groupIds
                        ));
                    } else if (student.getNif().equals(studentNIF)) {
                        student.addEnrollment(new EnrollmentIndividual(
                                semester,
                                course
                        ));
                    }
                }
            }
        }
    }

    public boolean updateEnrollmentMark(
            String course,
            String semester,
            String status,
            String studentNif,
            double mark
    ) {
        boolean isChanged = false;

        for (School school : schools) {
            for (Group group : school.getGroups()) {
                for (UserStudent student : group.getStudents()) {
                    if (student.getNif().equals(studentNif)) {
                        for (Enrollment enrollment : student.getEnrollments()) {
                            if (
                                    enrollment.getCourse().getName().equals(course) &&
                                    enrollment.getSemester().equals(semester) &&
                                    enrollment.getStatus().equals(tEnrollmentStatus.valueOf(status))
                            ) {
                                enrollment.setMark(mark);
                                enrollment.setStatus(tEnrollmentStatus.COMPLETED);
                                isChanged = true;
                            }
                        }
                    }
                }
            }
        }

        return isChanged;
    }

    /**
     * Get the list of schools
     * @return List of schools
     */
    public List<String> getSchools() {
        List<String> schoolsList = new ArrayList<>();
        return schoolsList;
    }

    /**
     * Get the list of teachers
     * @return List of teachers
     */
    public List<String> getTeachers() {
        List<String> teachersList = new ArrayList<>();
        return teachersList;
    }

    /**
     * Get the list of courses
     * @return List of courses
     */
    public List<String> getCourses() {
        List<String> coursesList = new ArrayList<>();
        return coursesList;
    }

    /**
     * Get the list of groups for a school
     * @param schoolName Name of the school
     * @return List of groups
     */
    public List<String> getGroups(String schoolName) {
        List<String> groupsList = new ArrayList<>();
        return groupsList;
    }

    /**
     * Get the list of students for a group
     * @param schoolName Name of the school
     * @param groupName Name of the group
     * @return List of students
     */
    public List<String> getStudents(String schoolName, String groupName) {
        List<String> studentsList = new ArrayList<>();
        return studentsList;
    }

    /**
     * Get the list of enrollments for a student
     * @param schoolName Name of the school
     * @param groupName Name of the group
     * @param studentNIF NIF of the student
     * @return List of enrollments
     */
    public List<String> getEnrollments(String schoolName, String groupName, String studentNIF) {
        List<String> enrollmentsList = new ArrayList<>();
        return enrollmentsList;
    }

    private UserTeacher getTeacher(String teacherNif) {
        for (UserTeacher teacher : teachers) {
            if (teacher.getNif().equals(teacherNif))
                return teacher;
        }

        return null;
    }

    private Course getCourse(String courseCode) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode))
                return course;
        }

        return null;
    }

}
