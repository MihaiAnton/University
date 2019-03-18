package UnitTests;

import Domain.Homework;
import Domain.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentTest {

    private Student s;

    @BeforeEach
    void setUp() {
        s = new Student("2229", "Mihai", 221, "mihai@example.com", "Teacher");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getId() {
        Assertions.assertEquals(s.getId(), "2229");
    }

    @Test
    void getName() {
        Assertions.assertEquals(s.getName(), "Mihai");
    }

    @Test
    void getEmail() {
        Assertions.assertEquals(s.getEmail(), "mihai@example.com");
    }

    @Test
    void getTeacher() {
        Assertions.assertEquals(s.getTeacher(), "Teacher");
    }

    @Test
    void getGroup() {
        Assertions.assertEquals(s.getGroup(), 221);
    }

    @Test
    void setId() {
        s.setId("2220");
        Assertions.assertEquals(s.getId(), "2220");
    }

    @Test
    void setName() {
        s.setName("Miihai");
        Assertions.assertEquals(s.getName(), "Miihai");
    }

    @Test
    void setGroup() {
        s.setGroup(211);
        Assertions.assertEquals(s.getGroup(), 211);
    }

    @Test
    void setEmail() {
        s.setEmail("abc@example.com");
        Assertions.assertEquals(s.getEmail(), "abc@example.com");
    }

    @Test
    void setTeacher() {
        s.setTeacher("Teacher2");
        Assertions.assertEquals(s.getTeacher(), "Teacher2");
    }

    @Test
    void getHomeworkList() {
        s.addHomework(new Homework(1,"MAP", 5, 7, 5));
        Assertions.assertNotEquals(s.getHomeworkList(), null);
    }

    @Test
    void addHomework() {
        Assertions.assertEquals(s.addHomework(new Homework(1,"MAP", 5, 7, 5)), null);

    }

    @Test
    void findHomework() {
        Homework h = new Homework(1,"MAP", 5, 7, 5);
        s.addHomework(h);
        Assertions.assertEquals(s.getRegister().findOne(1), h);
    }

    @Test
    void delayHomework() {
        Homework h = new Homework(1,"MAP", 5, 7, 5);
        s.addHomework(h);
        s.getRegister().findOne(1).setAssignmentWeek(6);
        Assertions.assertEquals(s.getRegister().findOne(1).getAssignmentWeek(), 6);
    }

    @Test
    void deleteHomework() {
        Homework h = new Homework(1,"MAP", 5, 7, 5);
        s.addHomework(h);
        s.getRegister().delete(1);
        Assertions.assertEquals(s.getRegister().findOne(1), null);
    }
}