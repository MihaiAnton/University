using CSApp.Domain;
using CSApp.Repository;
using CSApp.Service;
using CSApp.Validators;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace CSApp
{
    class Program
    {
        public static object StudentHomeworkRepository { get; private set; }

        static void Main(string[] args)
        {

            //repos
            string studentsFile = "M:\\School\\Metode Avansate de Programare\\CSApp\\CSApp\\DataFiles\\student.txt";
            StudentTxtRepository studentRepo = new StudentTxtRepository(new StudentValidator(), studentsFile);

            string homeworkFile = "M:\\School\\Metode Avansate de Programare\\CSApp\\CSApp\\DataFiles\\homework.txt";
            HomeworkTxtRepository homeworkRepo = new HomeworkTxtRepository(new HomeworkValidator(), homeworkFile);

            string gradeFile = "M:\\School\\Metode Avansate de Programare\\CSApp\\CSApp\\DataFiles\\grades.txt";
            GradeTxtRepository gradeRepo = new GradeTxtRepository(new GradeValidator(), gradeFile);

            //service
            TeacherService service = new TeacherService(studentRepo, homeworkRepo, gradeRepo); 

            //ui
            UI.UI ui = new UI.UI(service);

            //run the app
            ui.Run();

          


            Console.Read();
        }
    }
}
