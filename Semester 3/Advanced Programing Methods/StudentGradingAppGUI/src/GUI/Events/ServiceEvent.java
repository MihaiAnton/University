package GUI.Events;

import Domain.Homework;
import Domain.Student;
import Utils.Event;
import Utils.HomeworkEventType;
import Utils.StudentEventType;

public class ServiceEvent implements Event {

    private Student student, updated;
    private StudentEventType type;
    private Homework homework;
    private HomeworkEventType htype;

    public ServiceEvent(Student student, StudentEventType type){
        this.student = student;
        this.type = type;
    }

    public ServiceEvent(Homework homework, HomeworkEventType type){
        this.homework = homework;
        htype = type;
    }

    public void setUpdatedStudent(Student student){
        updated = student;
    }

    public StudentEventType getType(){
        return this.type;
    }

    public Student getStudent(){
        return this.student;
    }

    public Student getUpdatedStudent(){
        return updated;
    }

    public Homework getHomework(){
        return this.homework;
    }

    public HomeworkEventType getHtype(){
        return htype;
    }

}
