package UI;


import Domain.Student;
import Service.TeacherService;

import java.util.Scanner;

public class UI implements GenericUI{

    private TeacherService service;

    private StudentUI studentUI;
    private HomeworkUI homeworkUI;


    public UI(TeacherService teacherService){

        service = teacherService;
        studentUI = new StudentUI(service);
        homeworkUI = new HomeworkUI(service);
    }

    @Override
    public void run(){

        boolean inMenu = true;

        while(inMenu){

            //Print menu
            System.out.println(getMenu());

            try {

            //User choice
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();


                switch (choice) {
                    case 1:
                        printSpaces();
                        studentUI.run();
                        break;

                    case 2:
                        printSpaces();
                        homeworkUI.run();
                        break;

                    case 0:
                        inMenu = false;
                        break;


                }
            }
            catch(Exception e){}

        }

    }

    @Override
    public String getMenu() {
        String menu = "-----Main menu-----\n\n";
        menu = menu + "1 -  Student menu.\n";
        menu = menu + "2 -  Homework menu.\n";
        menu = menu + "0 -  Exit.\n";


        return menu;
    }

    private void printSpaces(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

}


