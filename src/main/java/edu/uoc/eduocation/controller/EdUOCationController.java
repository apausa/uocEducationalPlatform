package edu.uoc.eduocation.controller;

import edu.uoc.eduocation.model.course.Course;
import edu.uoc.eduocation.model.course.CourseException;
import edu.uoc.eduocation.model.course.CourseType;
import edu.uoc.eduocation.model.course.CourseWithExam.CourseWithExam;
import edu.uoc.eduocation.model.course.CourseWithExam.CourseWithExamException;
import edu.uoc.eduocation.model.course.CourseWithPracticeGroup.CourseWithPracticeGroup;
import edu.uoc.eduocation.model.course.CourseWithPracticeGroup.CourseWithPracticeGroupException;
import edu.uoc.eduocation.model.course.CourseWithPracticeIndividual.*;
import edu.uoc.eduocation.model.course.CourseWithoutExam.CourseWithoutExam;
import edu.uoc.eduocation.model.course.PracticeType;
import edu.uoc.eduocation.model.enrollment.Enrollment;
import edu.uoc.eduocation.model.enrollment.EnrollmentException;
import edu.uoc.eduocation.model.enrollment.EnrollmentStatus;
import edu.uoc.eduocation.model.enrollment.EnrollmentType;
import edu.uoc.eduocation.model.enrollment.enrollmentIndividual.EnrollmentIndividual;
import edu.uoc.eduocation.model.enrollment.enrollmentMultiple.EnrollmentMultiple;
import edu.uoc.eduocation.model.group.GroupException;
import edu.uoc.eduocation.model.school.SchoolException;
import edu.uoc.eduocation.model.school.location.Location;
import edu.uoc.eduocation.model.school.School;
import edu.uoc.eduocation.model.group.Group;
import edu.uoc.eduocation.model.school.location.LocationException;
import edu.uoc.eduocation.model.user.UserException;
import edu.uoc.eduocation.model.user.userStudent.UserStudent;
import edu.uoc.eduocation.model.user.userTeacher.UserTeacher;
import edu.uoc.eduocation.model.user.userTeacher.UserTeacherException;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Controller class for the EdUOCation application
 */
public class EdUOCationController {
    private final LinkedList<School> schools;
    private final LinkedList<UserTeacher> teachers;
    private final LinkedList<Course> courses;

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
        LinkedList<Location> list = addLocations(locations);

        try {
            schools.add(new School(name, list));
        } catch (SchoolException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
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
        try {
            teachers.add(new UserTeacher(
                    nif,
                    name,
                    surname,
                    birthdate,
                    department
            ));
        } catch (UserTeacherException | UserException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
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

        switch(CourseType.valueOf(type)) {
            case CourseWithoutExam:
                addCourseWithoutExam(
                        name,
                        code,
                        credits,
                        hours,
                        teacher
                );
                break;
            case CourseWithExam:
                addCourseWithExam(
                        name,
                        code,
                        credits,
                        hours,
                        teacher,
                        additionalInfo
                );
                break;
            case CourseWithPracticeGroup:
                addCourseWithPracticeGroup(
                        name,
                        code,
                        credits,
                        hours,
                        teacher,
                        additionalInfo
                );
                break;
            case CourseWithPracticeIndividual:
                addCourseWithPracticeIndividual(
                        name,
                        code,
                        credits,
                        hours,
                        teacher,
                        additionalInfo
                );
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
        // Get tutor from NIF
        UserTeacher tutor = getTeacher(tutorNIF);

        if (tutor == null)
            return;

        // Iterate over each school
        for (School currentSchool : schools) {
            if (currentSchool.getName().equals(schoolName)) {
                LinkedList<UserStudent> students = addStudents(studentData);

                try {
                    currentSchool.addGroup(new Group(
                            groupName,
                            tutor,
                            students
                    ));
                } catch (GroupException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
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
        // Get course from code
        Course course = getCourse(courseCode);

        if (course == null)
            return;

        for (School school : schools) {
            for (Group group : school.getGroups()) {

                // Iterate over group students
                for (UserStudent student : group.getStudents()) {
                    if (
                            student.getNif().equals(studentNIF) &&
                            EnrollmentType.valueOf(enrollmentType) == EnrollmentType.MULTIPLE
                    ) {
                        String[] groupIds = additionalInfo.split(",");

                        try {
                            // Add a multiple enrollment
                            student.addEnrollment(new EnrollmentMultiple(
                                    semester,
                                    course,
                                    groupIds
                            ));
                        } catch (UserException | EnrollmentException e) {
                            throw new RuntimeException(e);
                        }
                    } else if (
                            student.getNif().equals(studentNIF) &&
                            EnrollmentType.valueOf(enrollmentType) == EnrollmentType.INDIVIDUAL
                    ) {
                        try {
                            // Add an individual enrollment
                            student.addEnrollment(new EnrollmentIndividual(
                                    semester,
                                    course
                            ));
                        } catch (EnrollmentException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }

    /**
     * Updates a student's enrollment mark and status.
     * @param course the course name.
     * @param semester the semester.
     * @param status the enrollment status.
     * @param studentNif the student's NIF.
     * @param mark the new mark.
     * @return {@code true} if updated successfully, {@code false} otherwise.
     */
    public boolean updateEnrollmentMark(
            String course,
            String semester,
            String status,
            String studentNif,
            double mark
    ) {
        for (School school : schools) {
            for (Group group : school.getGroups()) {

                // Iterate over group students
                for (UserStudent student : group.getStudents()) {
                    if (student.getNif().equals(studentNif)) {

                        // Iterate over student enrollments
                        for (Enrollment enrollment : student.getEnrollments()) {
                            if (
                                    (enrollment.getCourse().getName().equals(course)) &&
                                    (enrollment.getSemester().equals(semester)) &&
                                    (mark >= 0 && mark <= 10)
                            ) {
                                enrollment.setMark(mark);
                                enrollment.setStatus(EnrollmentStatus.valueOf(status));

                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    /**
     * Get the list of schools
     * @return List of schools
     */
    public List<String> getSchools() {
        List<String> list = new ArrayList<>();

        for (School school : schools) {
            list.add(school.toString());
        }

        return list;
    }

    /**
     * Get the list of teachers
     * @return List of teachers
     */
    public List<String> getTeachers() {
        List<String> list = new ArrayList<>();

        for (UserTeacher teacher : teachers) {
            list.add(teacher.toString());
        }

        return list;
    }

    /**
     * Get the list of courses
     * @return List of courses
     */
    public List<String> getCourses() {
        List<String> list = new ArrayList<>();

        for (Course course : courses) {
            list.add(course.toString());
        }

        return list;
    }

    /**
     * Get the list of groups for a school
     * @param schoolName Name of the school
     * @return List of groups
     */
    public List<String> getGroups(String schoolName) {
        List<String> list = new ArrayList<>();

        // Iterate over each school
        for (School school : schools) {
            if (school.getName().equals(schoolName)) {

                // Iterate over school groups
                for (Group group : school.getGroups()) {
                    list.add(group.toString());
                }

                return list;
            }
        }

        return list;
    }

    /**
     * Get the list of students for a group
     * @param schoolName Name of the school
     * @param groupName Name of the group
     * @return List of students
     */
    public List<String> getStudents(String schoolName, String groupName) {
        List<String> list = new ArrayList<>();

        for (School school : schools) {
            if (school.getName().equals(schoolName)) {

                // Iterate over school groups
                for (Group group : school.getGroups()) {
                    if (group.getName().equals(groupName)) {

                        // Iterate over group students
                        for (UserStudent student : group.getStudents()) {
                            list.add(student.toString());
                        }

                        return list;
                    }
                }
            }
        }

        return list;
    }

    /**
     * Get the list of enrollments for a student
     * @param schoolName Name of the school
     * @param groupName Name of the group
     * @param studentNIF NIF of the student
     * @return List of enrollments
     */
    public List<String> getEnrollments(String schoolName, String groupName, String studentNIF) {
        List<String> list = new ArrayList<>();

        for (School school : schools) {
            if (school.getName().equals(schoolName)) {

                for (Group group : school.getGroups()) {
                    if (group.getName().equals(groupName)) {

                        // Iterate over group students
                        for (UserStudent student : group.getStudents()) {
                            if (student.getNif().equals(studentNIF)) {

                                // Iterate over student enrollments
                                for (Enrollment enrollment : student.getEnrollments()) {
                                    list.add(enrollment.toString());
                                }

                                return list;
                            }
                        }
                    }
                }
            }
        }

        return list;
    }

    /**
     * Converts an array of location strings into a list of Location objects.
     * @param locations array of location strings.
     * @return a list of Location objects.
     */
    private LinkedList<Location> addLocations(String[] locations) {
        LinkedList<Location> list = new LinkedList<>();

        for (String location : locations) {
            String[] arguments = location.split(",");

            try {
                list.add(new Location(
                        arguments[0], // address
                        arguments[1], // city
                        arguments[2], // country
                        arguments[3]  // phoneNumber
                ));
            } catch (LocationException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return list;
    }

    /**
     * Retrieves a teacher by their NIF.
     * @param teacherNif the NIF of the teacher.
     * @return the matching UserTeacher, or {@code null} if not found.
     */
    private UserTeacher getTeacher(String teacherNif) {
        for (UserTeacher teacher : teachers) {
            if (teacher.getNif().equals(teacherNif))
                return teacher;
        }

        return null;
    }


    /**
     * Adds a course with an associated exam.
     * @param name the course name.
     * @param code the course code.
     * @param credits the number of credits.
     * @param hours the number of hours.
     * @param teacher the assigned teacher.
     * @param additionalInfo additional exam details.
     */
    private void addCourseWithExam(
            String name,
            String code,
            Integer credits,
            Integer hours,
            UserTeacher teacher,
            String additionalInfo
    ) {
        String[] arguments = additionalInfo.split(",");
        LocalDateTime dateTime = LocalDateTime.parse(arguments[0]);

        LocalDate date = dateTime.toLocalDate();
        LocalTime time = dateTime.toLocalTime();

        try {
            courses.add(new CourseWithExam(
                    name,
                    code,
                    credits,
                    hours,
                    teacher,
                    date,
                    time,
                    String.format("%s, %s", arguments[1], arguments[2]), // Location
                    arguments[3] // Room
            ));
        } catch (CourseException | CourseWithExamException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * Adds a course with an associated group practice.
     * @param name the course name.
     * @param code the course code.
     * @param credits the number of credits.
     * @param hours the number of hours.
     * @param teacher the assigned teacher.
     * @param additionalInfo additional group practice details.
     */
    private void addCourseWithPracticeGroup(
            String name,
            String code,
            Integer credits,
            Integer hours,
            UserTeacher teacher,
            String additionalInfo
    ) {
        String[] arguments = additionalInfo.split(",");

        try {
            courses.add(new CourseWithPracticeGroup(
                    name,
                    code,
                    credits,
                    hours,
                    teacher,
                    PracticeType.valueOf(arguments[0]), // Practice type
                    Integer.parseInt(arguments[1]) // Maximum students
            ));
        } catch (CourseException | CourseWithPracticeGroupException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    /**
     * Adds a course without an associated exam.
     * @param name the course name.
     * @param code the course code.
     * @param credits the number of credits.
     * @param hours the number of hours.
     * @param teacher the assigned teacher.
     */
    private void addCourseWithoutExam(
            String name,
            String code,
            Integer credits,
            Integer hours,
            UserTeacher teacher
    ) {
        try {
            courses.add(new CourseWithoutExam(
                    name,
                    code,
                    credits,
                    hours,
                    teacher
            ));
        } catch (CourseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * Adds a course with an associated individual practice.
     * @param name the course name.
     * @param code the course code.
     * @param credits the number of credits.
     * @param hours the number of hours.
     * @param teacher the assigned teacher.
     * @param additionalInfo the practice type.
     */
    private void addCourseWithPracticeIndividual(
            String name,
            String code,
            Integer credits,
            Integer hours,
            UserTeacher teacher,
            String additionalInfo
    ) {
        try {
            courses.add(new CourseWithPracticeIndividual(
                    name,
                    code,
                    credits,
                    hours,
                    teacher,
                    PracticeType.valueOf(additionalInfo)
            ));
        } catch (CourseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    /**
     * Converts an array of student data strings into a list of UserStudent objects.
     * @param studentData array of student data strings.
     * @return a list of UserStudent objects.
     */
    private LinkedList<UserStudent> addStudents(String[] studentData) {
        LinkedList<UserStudent> list = new LinkedList<>();

        for (String student: studentData) {
            String[] arguments = student.split(":");

            try {
                list.add(new UserStudent(
                        arguments[0], // nif
                        arguments[1], // name
                        arguments[2], // surname
                        LocalDate.parse(arguments[3]) // birthdate
                ));
            } catch (UserException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return list;
    }

    /**
     * Retrieves a course by its code.
     * @param courseCode the code of the course.
     * @return the matching Course, or {@code null} if not found.
     */
    private Course getCourse(String courseCode) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode))
                return course;
        }

        return null;
    }
}
