package UI;

import Domain.Homework;
import Service.TeacherService;

import java.util.Scanner;

public class HomeworkUI implements GenericUI {

    private TeacherService service;

    public HomeworkUI(TeacherService teacherService){
        service = teacherService;
    }


    @Override
    public void run() {
        boolean inMenu = true;

        while(inMenu){

            System.out.println(getMenu());

            try {
                //User input

                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                System.out.println("Choice: " + choice);

                switch (choice) {
                    case 1:
                        System.out.println(addHomework());
                        AnyKeyToContinue();
                        printSpaces();
                        break;
                    case 2:
                        System.out.println(updateHomework());
                        AnyKeyToContinue();
                        printSpaces();
                        break;

                    case 3:
                        System.out.println(service.getHomeworkList());
                        AnyKeyToContinue();
                        printSpaces();
                        break;
                    case 0:
                        inMenu = false;
                        printSpaces();
                        break;

                    default:
                        break;


                }
            }
            catch(Exception e){}

        }
    }

    @Override
    public String getMenu() {
        String menu = "";
        menu = menu + "-----Homework Menu-----\n\n";
        menu = menu + "1 -  Add homework.\n";
        menu = menu + "2 -  Update homework.\n";
        menu = menu + "3 -  Show all.\n";
        menu = menu + "0 -  Back.\n";
        return menu;
    }


    private Homework readHomework(){
        Scanner s = new Scanner(System.in);

        String buff;
        int id, targetWeek, deadlineWeek, assignmentWeek;
        String desc;

        System.out.println("Homework id: ");buff = s.nextLine();id = Integer.parseInt(buff);
        System.out.println("Description: ");desc = s.nextLine();
        System.out.println("Target week: ");buff = s.nextLine();targetWeek = Integer.parseInt(buff);
        System.out.println("Deadline week: ");buff = s.nextLine();deadlineWeek = Integer.parseInt(buff);

        return new Homework(id, desc, targetWeek, deadlineWeek, targetWeek);
    }

    private String addHomework(){
        Homework homework;
        try {
            homework = readHomework();
        }
        catch (Exception e){
            return "Invalid data.";
        }

        try{
            if(this.service.addHomework(homework)==null)
                return "Homework added.";
            return "Homework id existing.";

        }
        catch(Exception e){
            return e.getMessage();
        }
    }

    private String updateHomework(){
        Homework homework;
        try {
            homework = readHomework();
        }
        catch (Exception e){
            return "Invalid data.";
        }

        try{
            if(this.service.updateHomework(homework)!=homework)
                return "Homework updated.";
            return "Homework id not existing. Try adding the homework instead.";

        }
        catch(Exception e){
            return e.getMessage();
        }
    }


    private void AnyKeyToContinue(){
        System.out.println("Press any key to continue.");
        try{
            System.in.read();
        }
        catch (Exception e){}
    }

    private void printSpaces(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}
