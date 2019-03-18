package Tests;

import Domain.Homework;
import Domain.Student;
import Exceptions.StudentException;
import Validators.StudentValidator;

public class StudentTester implements GenericTester{

    /**
     *  Test class specialized in Students
     *
     */

    /**
     *
     * @throws Exception if any Student class error
     */
    @Override
    public void test() /*throws Exception*/{

        String err = "";

        try {
            testGetters();
        }
        catch (Exception e){
            err = err + e.getMessage() + "\n";
        }

        try {
            testSetters();
        }
        catch (Exception e){
           err = err + e.getMessage() + "\n";
        }

        try {
            testRegister();
        }
        catch (Exception e){
            err = err + e.getMessage() + "\n";
        }

        try{
            testValidator();
        }
        catch(Exception e){
            err = err + e.getMessage() + "\n";
        }

        if(err.length() > 0){
            throw new StudentException(err);
        }

    }

    /**
     *
     * @throws Exception if any Student class getter produce errors
     * if id is null.
     */
    private void testGetters() /*throws StudentException*/{
        Student s = new Student("2229", "Mihai", 221, "mihai@example.com", "Teacher");

        try{
            assert s.getId().equals("2229");
            assert s.getEmail().equals("mihai@example.com");
            assert s.getName().equals("Mihai");
            assert s.getGroup() == 221;
            assert s.getTeacher().equals("Teacher");
        }
        catch(AssertionError e){
            throw new StudentException("Student getters error.");
        }
    }

    /**
     *
     * @throws Exception if any Student class setter produce errors
     * if id is null.
     */
    private void testSetters() /*throws StudentException*/ {
        Student s = new Student("2229", "Mihai", 221, "mihai@example.com", "Teacher");
        s.setEmail("abc@example.com");
        s.setGroup(211);
        s.setName("Miihai");
        s.setTeacher("Teacher2");
        s.setId("2220");

        try {
            assert s.getId().equals("2220");
            assert s.getEmail().equals("abc@example.com");
            assert s.getName().equals("Miihai");
            assert s.getGroup() == 211;
            assert s.getTeacher().equals("Teacher2");
        } catch (AssertionError e) {
            throw new StudentException("Student setters error.");
        }
    }

    /**
     *
     * @throws Exception if the Student register produces errors
     * if id is null.
     */
    private void testRegister() /*throws StudentException, Exception*/{
        Student s = new Student("2229", "Mihai", 221, "mihai@example.com", "Teacher");
        s.addHomework(new Homework(1,"MAP", 3, 5, 3));
        s.addHomework(new Homework(2,"ASC", 4, 7, 5));

        double grade = 0;
        for(Homework h : s.getHomeworkList()){
            grade += h.getGrade();
        }

        try{
            assert grade == 17.5;
            s.delayHomework(1);
            assert s.findHomework(1).getGrade() == 7.5;
            s.delayHomework(1);
            assert s.findHomework(1).getGrade() == 5;
            s.delayHomework(1);
            assert s.findHomework(1).getGrade() == 1;
        }
        catch (AssertionError e){
            throw new StudentException("Student grading error.");
        }
    }

    private void testValidator(){
        Student s = new Student("229", "Mihai", 2221, "mihai@example.com", "Teacher");
        StudentValidator val = new StudentValidator();
        try{
            val.validate(s);
            assert false;
        }
        catch (Exception e){
            assert true;
        }

    }

}











































