package Tests;

import Domain.Homework;
import Exceptions.HomeworkException;

/**
 *  Test class specialized in Homework
 */


public class HomeworkTest implements GenericTester{


    /**
     *
     * @throws Exception if any Homework class error
     */
    @Override
    public void test() /*throws Exception*/{
        String err = "";

        try{
            testGetters();
        }
        catch (Exception e){
            err = err + e.getMessage() + "\n";
        }

        try{
            testSetters();
        }
        catch (Exception e){
            err = err + e.getMessage() + "\n";
        }

        try{
            testGradingSystem();
        }
        catch (Exception e){
            err = err + e.getMessage() + "\n";
        }

        if(err.length() > 0){
            throw new HomeworkException(err);
        }

    }

    /**
     *
     * @throws Exception if any Homework class getter produce errors
     * if id is null.
     */
    private void testGetters() throws Exception{
        Homework h = new Homework(1,"MAP",5,7,5);
        try {

            assert h.getTargetWeek() == 5;
            assert h.getAssignmentWeek() == 5;
            assert h.getDeadlineWeek() == 7;
            assert h.getDescription().equals("MAP");
            assert h.getGrade() == 10;
            assert h.getId() == 1;
        }
        catch (AssertionError e){
            throw new HomeworkException("Homework getters error.");
        }
    }

    /**
     *
     * @throws Exception if any Homework class setter produce errors
     * if id is null.
     */
    private void testSetters() throws Exception{
        Homework h = new Homework(1,"MAP",5,7,5);

        h.setAssignmentWeek(7);
        h.setDeadlineWeek(8);
        h.setDescription("ASC");
        h.setTargetWeek(4);

        try {

            assert h.getTargetWeek() == 4;
            assert h.getAssignmentWeek() == 7;
            assert h.getDeadlineWeek() == 8;
            assert h.getDescription().equals("ASC");
            assert h.getGrade() == 1;
            assert h.getId() == 1;
        }
        catch (AssertionError e){
            throw new HomeworkException("Homework setters error.");
        }
    }

    /**
     *
     * @throws Exception if the Homework class grading system produces errors
     * if id is null.
     */
    private void testGradingSystem() throws Exception {
        Homework h = new Homework(1, "MAP", 5, 7, 5);

        try {
            assert h.getGrade() == 10;
            h.setAssignmentWeek(6);
            assert h.getGrade() == 7.5;
            h.setAssignmentWeek(7);
            assert h.getGrade() == 5;
            h.setAssignmentWeek(8);
            assert h.getGrade() == 1;
        } catch (AssertionError e) {
            throw new HomeworkException("Homework grading error.");
        }
    }
}




















