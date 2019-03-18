package Controller;

import Service.TeacherService;

public class MainController {

    private TeacherService service;

    private StudentController studCtrl;
    private HomeworkController hwCtrl;


    public MainController(TeacherService service){
        this.service = service;
        this.studCtrl = new StudentController(service);
    }

    public TeacherService getService(){
        return this.service;
    }

    public StudentController getStudCtrl(){
        return this.studCtrl;
    }

    public HomeworkController getHomeworkCtrl(){
        return this.hwCtrl;
    }


}
