package edu.uoc.eduocation.controller;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EdUOCationControllerTest {

    private EdUOCationController controller;

    @BeforeEach
    void setUp() {
        controller = new EdUOCationController(
                "schools_test.txt",
                "teachers_test.txt",
                "courses_test.txt",
                "students_test.txt",
                "enrollments_test.txt"
        );
    }

    // BASIC TESTS
    @Test
    @Tag("basic")
    @Order(1)
    void testAddSchool() {
        controller.addSchool("New School", "Main Street,Barcelona,Spain,123456789");
        controller.addSchool("Another School", "Second Street,Barcelona,Spain,987654321");
        List<String> schools = controller.getSchools();

        assertTrue(schools.stream().anyMatch(s -> s.contains("New School")));
        assertTrue(schools.stream().anyMatch(s -> s.contains("Another School")));
        assertFalse(schools.isEmpty());
    }

    @Test
    @Tag("basic")
    @Order(2)
    void testAddTeacher() {
        controller.addTeacher("00000000Z", "Alice", "Johnson", java.time.LocalDate.of(1990, 1, 1), "Physics");
        controller.addTeacher("11111111Y", "Bob", "Williams", java.time.LocalDate.of(1985, 5, 15), "Mathematics");
        List<String> teachers = controller.getTeachers();

        assertTrue(teachers.stream().anyMatch(t -> t.contains("Alice")));
        assertTrue(teachers.stream().anyMatch(t -> t.contains("Bob")));
        assertFalse(teachers.isEmpty());
    }

    @Test
    @Tag("basic")
    @Order(3)
    void testGetSchools() {
        List<String> schools = controller.getSchools();

        assertFalse(schools.isEmpty());
        assertTrue(schools.stream().anyMatch(s -> s.contains("UOC Central")));
        assertTrue(schools.stream().anyMatch(s -> s.contains("UOC North Campus")));
    }

    @Test
    @Tag("basic")
    @Order(4)
    void testGetTeachers() {
        List<String> teachers = controller.getTeachers();

        assertFalse(teachers.isEmpty());
        assertTrue(teachers.stream().anyMatch(t -> t.contains("John")));
        assertTrue(teachers.stream().anyMatch(t -> t.contains("Jane")));
    }

    @Test
    @Tag("basic")
    @Order(5)
    void testGetCourses() {
        List<String> courses = controller.getCourses();

        assertFalse(courses.isEmpty());
        assertTrue(courses.stream().anyMatch(c -> c.contains("Algorithms and Data Structures")));
        assertTrue(courses.stream().anyMatch(c -> c.contains("Organic Chemistry")));
    }

    @Test
    @Tag("basic")
    @Order(6)
    void testGetGroups() {
        List<String> groups = controller.getGroups("UOC Central");

        assertFalse(groups.isEmpty());
        assertTrue(groups.stream().anyMatch(g -> g.contains("Group A")));
        assertTrue(groups.stream().anyMatch(g -> g.contains("Group F")));
    }

    // ADVANCED TESTS
    @Test
    @Tag("advanced")
    @Order(7)
    void testAddCourse() {
        controller.addCourse("CourseWithoutExam", "New Course", "NEW101", 5, 60, "12345678A", null);
        controller.addCourse("CourseWithExam", "Another Course", "NEW102", 6, 90, "12345678A", "2024-05-01T10:00,UOC Central,Av. Tibidabo 39,Room 1");
        List<String> courses = controller.getCourses();

        assertTrue(courses.stream().anyMatch(c -> c.contains("New Course") && c.contains("NEW101")));
        assertTrue(courses.stream().anyMatch(c -> c.contains("Another Course") && c.contains("NEW102")));
    }

    @Test
    @Tag("advanced")
    @Order(8)
    void testAddStudentGroup() {
        controller.addStudentGroup("UOC Central", "New Group", "12345678A",
                new String[]{"11111111A:Test:Student:2000-01-01"});
        controller.addStudentGroup("UOC North Campus", "Another Group", "12345678A",
                new String[]{"22222222B:Second:Student:1999-12-12"});
        List<String> groupsCentral = controller.getGroups("UOC Central");
        List<String> groupsNorth = controller.getGroups("UOC North Campus");

        assertTrue(groupsCentral.stream().anyMatch(g -> g.contains("New Group")));
        assertTrue(groupsNorth.stream().anyMatch(g -> g.contains("Another Group")));
    }

    @Test
    @Tag("advanced")
    @Order(9)
    void testAddEnrollment() {
        controller.addEnrollment("98765432B", "CHEM201", "2023A", "INDIVIDUAL", "");
        controller.addEnrollment("65432109E", "CS101", "2023A", "INDIVIDUAL", "");
        List<String> enrollmentsGroupA = controller.getEnrollments("UOC Central", "Group A", "98765432B");
        List<String> enrollmentsGroupB = controller.getEnrollments("UOC North Campus", "Group B", "65432109E");

        assertTrue(enrollmentsGroupA.stream().anyMatch(e -> e.contains("Organic Chemistry")));
        assertTrue(enrollmentsGroupB.stream().anyMatch(e -> e.contains("Algorithms and Data Structures")));
    }

    @Test
    @Tag("advanced")
    @Order(10)
    void testUpdateEnrollmentMark() {
        boolean updatedMarkFalse1 = controller.updateEnrollmentMark("Algorithms and Data Structures", "2023A", "IN_PROGRESS", "98765432B", -0.1);
        boolean updatedMarkFalse2 = controller.updateEnrollmentMark("Algorithms and Data Structures", "2023A", "IN_PROGRESS", "98765432B", 10.1);

        assertFalse(updatedMarkFalse1);
        assertFalse(updatedMarkFalse2);

        boolean updatedMark1 = controller.updateEnrollmentMark("Algorithms and Data Structures", "2023A", "IN_PROGRESS", "98765432B", 9.5);
        boolean updatedMark2 = controller.updateEnrollmentMark("Organic Chemistry", "2023B", "IN_PROGRESS", "76543210D", 8.0);

        assertTrue(updatedMark1);
        assertTrue(updatedMark2);
    }

    @Test
    @Tag("advanced")
    @Order(11)
    void testGetStudents() {
        List<String> studentsGroupA = controller.getStudents("UOC Central", "Group A");
        List<String> studentsGroupF = controller.getStudents("UOC Central", "Group F");

        assertFalse(studentsGroupA.isEmpty());
        assertTrue(studentsGroupA.stream().anyMatch(s -> s.contains("John")));
        assertFalse(studentsGroupF.isEmpty());
        assertTrue(studentsGroupF.stream().anyMatch(s -> s.contains("Emily")));
    }

    @Test
    @Tag("advanced")
    @Order(12)
    void testGetEnrollments() {
        List<String> enrollmentsGroupA = controller.getEnrollments("UOC Central", "Group A", "98765432B");
        List<String> enrollmentsGroupF = controller.getEnrollments("UOC Central", "Group F", "32109876H");

        assertFalse(enrollmentsGroupA.isEmpty());
        assertTrue(enrollmentsGroupA.stream().anyMatch(e -> e.contains("Algorithms and Data Structures")));
        assertFalse(enrollmentsGroupF.isEmpty());
        assertTrue(enrollmentsGroupF.stream().anyMatch(e -> e.contains("Creative Writing")));
    }
}