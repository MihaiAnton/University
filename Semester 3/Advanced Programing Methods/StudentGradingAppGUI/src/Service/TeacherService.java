package Service;

import Domain.Grade;
import Domain.Homework;
import Domain.Student;
import GUI.Events.ServiceEvent;
import Repository.GenericRepository;
import Utils.HomeworkEventType;
import Utils.Observable;
import Utils.Observer;
import Utils.StudentEventType;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TeacherService implements Observer<ServiceEvent> {

    private GenericRepository<String, Student> studentRepo;
    private GenericRepository<Integer, Homework> homeworkRepo;
    private GenericRepository<Integer, Grade> gradeRepo;

    private String studentsPath = "M:\\School\\Metode Avansate de Programare\\Lab3\\src\\StudentFiles\\";
    ArrayList<Observable> observables;

    public TeacherService(GenericRepository<String, Student> sRepo, GenericRepository<Integer, Homework> hRepo,GenericRepository<Integer, Grade> gradeRepo){
        studentRepo = sRepo;
        homeworkRepo = hRepo;
        this.gradeRepo = gradeRepo;
        this.observables = new ArrayList<>();
    }

    //Student functions

    /**
     *
     * @param student
     * @return
     * @throws RuntimeException
     */
    public Student addStudent(Student student){
        try{
            return studentRepo.save(student);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws RuntimeException
     */
    public Student findStudent(String id){
        try{
            return studentRepo.findOne(id);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     *
     * @param student
     * @return
     * @throws RuntimeException
     */
    public Student updateStudent(Student student){
        try{
            return studentRepo.update(student);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws RuntimeException
     */
    public Student deleteStudent(String id){
        try{
            return studentRepo.delete(id);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    //Homework functions

    /**
     *
     * @param homework
     * @return
     * @throws RuntimeException
     */
    public Homework addHomework(Homework homework){
        try{
            return homeworkRepo.save(homework);

        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws RuntimeException
     */
    public Homework findHomework(int id){
        try{
            return homeworkRepo.findOne(id);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     *
     * @param homework
     * @return
     * @throws RuntimeException
     */
    public Homework updateHomework(Homework homework){
        try{
            return homeworkRepo.update(homework);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


    //Grading methods


    /**
     *
     * @param studentId
     * @param homeworkId
     * @return return the homework if already existing, or null
     * @throws RuntimeException
     */
    public Homework addStudentHomework(String studentId, int homeworkId){
        try{
            if (hasHomework(studentId, homeworkId)) {
                return homeworkRepo.findOne(homeworkId);
            }
            else{
                Student student = studentRepo.findOne(studentId);
                Homework homework = homeworkRepo.findOne(homeworkId);
                student.addHomework(homework);
                return null;
            }

        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     *
     * @param studentId
     * @param homeworkId
     * @return true if has homework, false otherwise
     * @throws RuntimeException
     */
    public boolean hasHomework(String studentId, int homeworkId){


        //Predicate<Student> hasHomework = (s,h) -> s.findHomework()

        try{
            Student student = studentRepo.findOne(studentId);
            if(student == null){
                throw new Exception("Student " + studentId + " not found.");
            }
            Homework homework = homeworkRepo.findOne(homeworkId);
            if(homework == null){
                throw new Exception("Homework " + homeworkId + " not found.");
            }

            //Both student and homework found
            if(student.findHomework(homework.getId()) != null){
                return true;
            }
            return false;

        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     *
     * @param studentId
     * @param homeworkId
     * @param grade
     * @throws RuntimeException
     */
    public void assignGrade(String studentId, int homeworkId, double grade, int assignmentWeek, String feedback){
        try{
            addStudentHomework(studentId, homeworkId);
            if(grade < 0 || grade > 10){
                throw new Exception("Incorrect grade. Grade must be between 0 and 10.");
            }

            if(assignmentWeek!= -1 && (assignmentWeek < 1 || assignmentWeek > 14)){
                throw new Exception("Incorrect week. Week must be between 1 and 14.");
            }

            Student student = studentRepo.findOne(studentId);
            Homework h = student.findHomework(homeworkId);

            if(assignmentWeek >= h.getTargetWeek() + 2){
                throw new Exception("Cant't assign homework. More than 2 weeks passed.");
            }


            if(assignmentWeek == -1){
                assignmentWeek = h.getAssignmentWeek();
            }

            h.setAssignmentWeek(assignmentWeek);
            h.setGrade(grade);
            Grade grade1 = new Grade(studentId, homeworkId, this.gradeRepo.getSize(), h.getGrade());
            gradeRepo.save(grade1);

            // Print assignment
            String filePath = studentsPath + student.getId() + ".txt";
            String text = "Tema: " + h.getId() + "\nPredata in saptamana: " + h.getAssignmentWeek() + "\nDeadline: " + h.getDeadlineWeek() + "\nFeedback: " + feedback + "\n\n";

            File f = new File(filePath);
            try{f.createNewFile();}
            catch (Exception e){}

            try{
                Files.write(Paths.get(filePath), text.getBytes() , StandardOpenOption.APPEND);
            }
            catch (Exception e){
            }


        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


    /*
    ”Tema:” numărTema
o ”Predată în săptămâna:” nrSăptămânăPredat
o ”Deadline:” nrSăptămânăDeadline
o ”Feedback:” aprecieri, sugestii, precizări în legătură cu depunctările efectuate



     */
    /**
     *
     * @param studentId
     * @param homeworkId
     * @return the grade
     * @throws RuntimeException
     */
    public double getGrade(String studentId, int homeworkId){
        try {
            if(!hasHomework(studentId, homeworkId)){
                throw new Exception("Student " + studentId + " doesn't have the homework " + homeworkId + ".");
            }
            else{
                Homework h = studentRepo.findOne(studentId).findHomework(homeworkId);
                return h.getGrade();
            }
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public String getHomeworkList(){
       /* String list = "";

        for(Homework h : this.homeworkRepo.findAll()){
            list = list + "Homework " + h.getId() + ": " + h.getDescription() + ", Target week: " + h.getTargetWeek() + ", Deadline week: " + h.getDeadlineWeek() + "\n";
        }
        return list;
        */

        String s = homeworkRepo.getValues().stream().map(Object::toString).collect(Collectors.joining("\n"));


        return s;

    }

    /**
     *
     * @param studentId
     * @param homeworkId
     * @return
     */
    public String delayStudentHomework(String studentId, int homeworkId){
        try{

            Student student = studentRepo.findOne(studentId);
            if(student == null){
                return("Inexistent student.");
            }
            else{
                Homework homework = homeworkRepo.findOne(homeworkId);
                if(homework == null){
                    return("Inexistent homework.");
                }

                if(student.findHomework(homeworkId) == null){
                    return("Student " + studentId + " doesn't have homework " + homeworkId + " assigned.");
                }

                student.delayHomework(homeworkId);
            }
        }
        catch(Exception e){
            return e.getMessage();
        }

        return("Homework delayed.");
    }


    @Override
    public void addObservable(Observable<ServiceEvent> observable) {
        this.observables.add(observable);
    }

    @Override
    public void removeObserver(Observable<ServiceEvent> observable) {
        this.observables.remove(observable);
    }

    @Override
    public void notifyObservers(ServiceEvent event) {
        for (Observable o:
             this.observables) {
            o.update(event);
        }
    }

    public Iterable<Student> getStudents() {
        return this.studentRepo.findAll();
    }

    public Homework deleteHomework(Homework hw) {
        return this.homeworkRepo.delete(hw.getId());
    }

    public Iterable<Homework> getHomeworkIterable() {
        return this.homeworkRepo.findAll();
    }

    public Iterable<Grade> getAllGrades(){
        return this.gradeRepo.findAll();
    }

}


































