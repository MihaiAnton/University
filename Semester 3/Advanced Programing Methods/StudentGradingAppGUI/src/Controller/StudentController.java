package Controller;

import Domain.Student;
import GUI.Events.ServiceEvent;
import Service.TeacherService;
import Utils.StudentEventType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentController implements Utils.Observable<ServiceEvent> {

    private TeacherService service;
    private ObservableList<Student> studentModel;



    public StudentController(TeacherService service) {
        this.service = service;
        this.service.addObservable(this);

        studentModel = FXCollections.observableArrayList();
        populateList();

    }

    private void populateList() {
        Iterable<Student> list = service.getStudents();
        list.forEach(x->studentModel.add(x));
    }


    @Override
    public void update(ServiceEvent event) {
        if(event.getType() == null)
            return;
        switch(event.getType()){
            case ADD:{studentModel.add(event.getStudent());break;}

            case UPDATE:{
                int index = 0;
                boolean found=false;
                for(index = 0;index<studentModel.size() && !found;index++){
                    if(studentModel.get(index).getId().equals(event.getStudent().getId())){
                        found = true;
                        studentModel.remove(index);
                    }
                }




                studentModel.add(event.getUpdatedStudent());
                break; }

            case DELETE: {
                int index = 0;
                boolean found = false;
                for (index = 0; index < studentModel.size() && !found; index++) {
                    if (studentModel.get(index).getId().equals(event.getStudent().getId())) {
                        found = true;
                        studentModel.remove(index);
                    }
                }
            }
        }
    }


    public void addStudent(Student s){
        if(service.addStudent(s) == null) {
            service.notifyObservers(new ServiceEvent(s, StudentEventType.ADD));
        }
    }

    public void removeStudent(Student s){
        if(service.deleteStudent(s.getId()) != null) {
            service.notifyObservers(new ServiceEvent(s, StudentEventType.DELETE));
        }
    }

    public void updateStudent(Student newS){
        service.updateStudent(newS);

        ServiceEvent event = new ServiceEvent(newS,StudentEventType.UPDATE);
        event.setUpdatedStudent(newS);
        service.notifyObservers(event);

    }

    public ObservableList getStudentModel(){
        return this.studentModel;
    }


    public void gradeStudent(String id, int hid, double grade, int week) {
        for (Student s:
             service.getStudents()) {
            if(s.getId().equals(id) || s.getName().equals(id))   {
                service.assignGrade(s.getId(), hid,grade, week, "");
            }
        }

    }
}
