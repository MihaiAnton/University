package UI;

import Domain.Student;
import Service.TeacherService;

import java.util.Scanner;

public class StudentUI implements GenericUI{

    private TeacherService service;
    public StudentUI(TeacherService teacherService){
        service = teacherService;
    }


    @Override
    public void run(){
        boolean inMenu = true;

        while(inMenu){
            //Print menu
            System.out.println(getMenu());

            try {

            //User input
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            System.out.println("Choice: " + choice);


                switch (choice) {
                    case 1:
                        System.out.println(this.addStudent());
                        AnyKeyToContinue();
                        printSpaces();
                        break;

                    case 2:
                        System.out.println(this.updateStudent());
                        AnyKeyToContinue();
                        printSpaces();
                        break;

                    case 3:
                        System.out.println(this.removeStudent());
                        AnyKeyToContinue();
                        printSpaces();
                        break;

                    case 4:
                        System.out.println(this.gradeStudent());
                        AnyKeyToContinue();
                        printSpaces();
                        break;

                    case 5:
                        System.out.println(this.addHomework());
                        scanner.nextByte();
                        printSpaces();
                        break;

                    case 6:
                        System.out.println(this.checkGrade());
                        AnyKeyToContinue();
                        printSpaces();
                        break;

                    case 7:
                        System.out.println(this.delayHomework());
                        AnyKeyToContinue();
                        printSpaces();
                        break;


                    case 0:
                        inMenu = false;
                        printSpaces();


                    default:
                        break;

                }
            }
            catch(Exception e){}



        }
    }

    @Override
    public String getMenu(){
        String menu = "";
        menu = menu + "-----Student Menu-----\n\n";
        menu = menu + "1 -  Add student.\n";
        menu = menu + "2 -  Update student.\n";
        menu = menu + "3 -  Remove student.\n";
        menu = menu + "4 -  Grade student.\n";
        menu = menu + "5 -  Add homework.\n";
        menu = menu + "6 -  Check grade.\n";
        menu = menu + "7 -  Delay homework.\n";
        menu = menu + "0 -  Back.\n";
        return menu;

    }

    private Student readStudent(){

        Scanner s = new Scanner(System.in);

        System.out.println("Student id: ");String id = s.nextLine();
        System.out.println("Student name: ");String name = s.nextLine();
        System.out.println("Student group: ");String group = s.nextLine();
        //s.nextByte();
        System.out.println("Student mail: ");String mail = s.nextLine();
        System.out.println("Student teacher: ");String teacher = s.nextLine();

        Student student = new Student(id, name, Integer.parseInt(group), mail, teacher);
        return student;
    }

    private String addStudent(){
        Student student;
        try{
            student = readStudent();
        }
        catch (Exception e){
            return "Invalid data.";
        }
        String rez = "";
        try{
            if(service.addStudent(student) == student){
                return "Student id already exist.";
            }
            return "Student added.";
        }
        catch(Exception e){
           return e.getMessage();
        }

    }

    private String updateStudent(){
        Student student;
        try{
            student = readStudent();
        }
        catch (Exception e){
            return "Invalid data.";
        }

        try{
            if(service.updateStudent(student) == null) {
                return "Student updated.";
            } else
                return "Student id not existing. Try adding the student instead.";
        }
        catch(Exception e){
            return e.getMessage();
        }
    }

    private String removeStudent(){
        String id;
        Scanner s = new Scanner(System.in);
        System.out.println("Student id: ");
        id = s.nextLine();

        try{
            if(service.deleteStudent(id) == null) {
                return "Student id does not exist.";
            }
            else
                return "Student removed.";
        }
        catch(Exception e){
            return e.getMessage();
        }
    }
    private String gradeStudent(){
        String id, feedback;
        int hId, week;
        double grade;
        String gr;

        Scanner s = new Scanner(System.in);
        System.out.println("Student id: ");id = s.nextLine();

        System.out.println("Homework id: ");gr = s.nextLine();
        hId = Integer.parseInt(gr);
        System.out.println("Grade: ");gr = s.nextLine();
        grade = Double.parseDouble(gr);
        System.out.println("Week: ");gr = s.nextLine();
        week = Integer.parseInt(gr);
        System.out.println("Feedback: ");feedback = s.nextLine();

        try{
            service.assignGrade(id, hId, grade, week, feedback);
            return "Grade assigned.";
        }
        catch(Exception e){
            return e.getMessage();
        }

    }
    private String addHomework(){
        String id;
        int hId;

        Scanner s = new Scanner(System.in);
        System.out.println("Student id: ");id = s.nextLine();
        System.out.println("Homework id: ");hId = s.nextInt();

        try{
            service.addStudentHomework(id, hId);
            return "Homework added.";
        }
        catch(Exception e){
            return e.getMessage();
        }

    }
    private String checkGrade(){
        String id;
        int hId;

        Scanner s = new Scanner(System.in);
        System.out.println("Student id: ");id = s.nextLine();
        System.out.println("Homework id: ");hId = s.nextInt();

        try{
            double grade = service.getGrade(id, hId);
            return "Grade: " + grade;
        }
        catch(Exception e){
            return e.getMessage();
        }
    }

    private String delayHomework(){
        String id;
        int hId;

        Scanner s = new Scanner(System.in);
        System.out.println("Student id: ");id = s.nextLine();
        System.out.println("Homework id: ");hId = s.nextInt();

        try{
            return service.delayStudentHomework(id,hId);
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

































