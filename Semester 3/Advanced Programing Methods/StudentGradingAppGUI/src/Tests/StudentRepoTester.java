package Tests;

import Domain.Student;
import Exceptions.StudentRepoException;
import Exceptions.ValidationException;
import Repository.StudentRepository;
import Validators.StudentValidator;

public class StudentRepoTester implements GenericTester{


    /**
     *  Test class specialized in StudentRepo
     */

    /**
     *
     * @throws Exception if any StudentRepo class error
     */
    @Override
    public void test() /*throws Exception*/{
        testCRUD();
        testExceptions();
    }

    private void testExceptions(){
        StudentValidator validator = new StudentValidator();
        StudentRepository repo = new StudentRepository(validator);

        try{
            repo.findOne(null);
            assert false;
        }
        catch (Exception e){
            assert true;
        }


    }


    /**
     *
     * @throws ValidationException if any validation fails
     * @throws StudentRepoException if any method generates errors
     */
    private void testCRUD()/* throws ValidationException, StudentRepoException*/ {

        StudentValidator validator = new StudentValidator();
        StudentRepository repo = new StudentRepository(validator);



        try{
            assert(repo.save(new Student("2229", "Mihai", 221, "mihai@example.com", "Teacher")) == null);
            assert(repo.save(new Student("2225", "Name", 225, "name@example.com", "Teacher2")) == null);
            Student extern = new Student("2224", "Student", 231, "student@example.com", "Teacher3");

            //Test findOne
            assert repo.findOne(extern.getId()) == null;
            Student s = repo.findOne("2229");
            assert s.getId().equals("2229");
            assert s.getName().equals("Mihai");
            assert s.getGroup() == 221;
            assert repo.findOne("2225") != null;


            //Test double add
            assert(repo.save(extern) == null);
            assert(repo.save(extern) == extern);

            //Test update
            Student updStud = new Student("1234", "Update", 333 , "Email@test.com","Teacher4");
            assert(repo.update(updStud) == updStud);
            assert(repo.save(updStud) == null);
            updStud.setName("StudentUpdated");

            assert(repo.update(updStud) == null);
            assert(repo.findOne(updStud.getId()).getName().equals("StudentUpdated"));

            //Test delete
            boolean appeared = false;
            for(Student st : repo.findAll()){
                if(st.getName().equals("StudentUpdated"))
                    appeared = true;
            }
            assert appeared;

            assert(repo.delete(updStud.getId()) == updStud);
            assert(repo.delete(updStud.getId()) == null);

            appeared = false;
            for(Student st : repo.findAll()){
                if(st.getName().equals("StudentUpdated"))
                    appeared = true;
            }
            assert !appeared;



        }
        catch (AssertionError e){
            throw new StudentRepoException("Student repo error.");
        }

    }


}



















