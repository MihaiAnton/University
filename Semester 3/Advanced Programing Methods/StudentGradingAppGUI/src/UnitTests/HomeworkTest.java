package UnitTests;

import Domain.Homework;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HomeworkTest {

    private Homework h;

    @BeforeEach
    void setUp() {
        h = new Homework(1,"MAP",5,7,5);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getId() {
        Assertions.assertEquals((long)h.getId(),1);
    }

    @Test
    void setId() {
        h.setId(2);
        Assertions.assertEquals((long)h.getId(), 2);
    }

    @Test
    void getDescription() {
        Assertions.assertEquals(h.getDescription(), "MAP");
    }

    @Test
    void setDescription() {
        h.setDescription("ASC");
        Assertions.assertEquals(h.getDescription(), "ASC");
    }

    @Test
    void getTargetWeek() {
        Assertions.assertEquals(h.getTargetWeek(),5);
    }

    @Test
    void setTargetWeek() {
        h.setTargetWeek(6);
        Assertions.assertEquals(h.getTargetWeek(),6);
    }

    @Test
    void getDeadlineWeek() {
        Assertions.assertEquals(h.getDeadlineWeek(), 7);
    }

    @Test
    void setDeadlineWeek() {
        h.setDeadlineWeek(7);
        Assertions.assertEquals(h.getDeadlineWeek(),7);
    }

    @Test
    void getAssignmentWeek() {
        Assertions.assertEquals(h.getAssignmentWeek(), 5);
    }

    @Test
    void setAssignmentWeek() {
        h.setAssignmentWeek(8);
        Assertions.assertEquals(h.getAssignmentWeek(),8);
    }

    @Test
    void getGrade() {
        /*
        Assertions.assertEquals(h.getGrade()-10 > , 10);
        h.setAssignmentWeek(6);
        Assertions.assertEquals(h.getGrade(), 7.5);
        h.setAssignmentWeek(7);
        Assertions.assertEquals(h.getGrade(), 5);
        h.setAssignmentWeek(8);
        Assertions.assertEquals(h.getGrade(), 1);
        */

    }

    @Test
    void setGrade() {
       /* h.setGrade(2);
        Assertions.assertEquals(h.getGrade(), 2);
        */
    }
}