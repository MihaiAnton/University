


package Domain;

import Exceptions.StudentException;
import Repository.Register;
import Validators.HomeworkValidator;
import Domain.Grade;

import java.util.ArrayList;
import java.util.function.Predicate;


public class Student implements IdEntity<String>{

    private String id;
    private String name;
    private int group;
    private String email;
    private String teacher;
    private Register register;
    ArrayList<Grade> grades;

    Predicate<Student> isNulls = s -> s == null;
    Predicate<Homework> isNullh = h -> h == null;

    //Constructors
    public Student(String id, String name, int group, String email, String teacher){

        this.id = id;
        this.name = name;
        this.group = group;
        this.email = email;
        this.teacher = teacher;
        this.register = new Register(new HomeworkValidator());
        grades = new ArrayList<>();
    }

    //Getters
    @Override
    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public String getTeacher(){
        return this.teacher;
    }

    public int getGroup(){
        return this.group;
    }

    public Register getRegister(){
        return this.register;
    }

    //Setters
    @Override
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }




    //Homework methods

    /**
     *
     * @return all entities
     */
    public Iterable<Homework> getHomeworkList(){
        return this.register.findAll();
    }

    /**
     *
     * @param homework
     * @return null if homework saved,
     *          the entity otherwise
     */
    public Homework addHomework(Homework homework) /*throws Exception*/{
        try{
            return this.register.save(homework);
        }
        catch (Exception e){
            throw new StudentException(e.getMessage());
        }
    }

    /**
     *
     * @param id
     * @return the homework
     *          else null
     */
    public Homework findHomework(int id) /*throws Exception*/{

        try{
            Homework one = this.register.findOne(id);
            return one;
        }
        catch (Exception e){
            throw new StudentException(e.getMessage());
        }
    }
    /**
     *   Delays the homework with one week
    * */
    public void delayHomework(int id) /*throws Exception*/{
        Homework h = this.register.findOne(id);

        if(isNullh.test(h)){
            throw new StudentException("Inexistent homework id.");
        }

        h.setAssignmentWeek(h.getAssignmentWeek() + 1);
    }

    /**
     *
     * @param id
     * @return the homework if existent id
     * @throws StudentException if id is inexistent
     */
    public Homework deleteHomework(int id) /*throws Exception*/{
        Homework h = this.register.findOne(id);

        if(isNullh.test(h)){
            throw new StudentException("Inexistent homework id.");
        }

        this.register.delete(id);
        return h;
    }
}





















