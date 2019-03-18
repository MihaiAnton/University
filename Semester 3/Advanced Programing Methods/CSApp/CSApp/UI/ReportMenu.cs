using CSApp.Domain;
using CSApp.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSApp.UI
{
    class ReportMenu
    {
        private TeacherService service;

        public ReportMenu(TeacherService service)
        {
            this.service = service;
        }

        public void Run()
        {
            bool inMenu = true;
            int command;

            while (inMenu)
            {
                PrintSpaces();
                Console.Write(GetMenu());
                try
                {
                    command = Int32.Parse(Console.ReadLine());
                }
                catch (Exception e) { continue; }


                switch (command)
                {
                    case 1:
                        foreach(var pair in service.ReportStudentsAvgGrades())
                        {
                            Console.WriteLine(pair.Key.Name + " average: " + pair.Value);
                        }
                        pressEnterToContinue();
                        break;

                    case 2:
                        Homework result = service.ReportHardestHomework();
                        Console.WriteLine(result.GetId() + " Description: " + result.Description);
                        pressEnterToContinue();
                        break;

                    case 3:
                        foreach(var s in service.ReportExamAbleStudents())
                        {
                            Console.WriteLine("Student: " + s.GetId() + " Name: " + s.Name);
                        }
                        pressEnterToContinue();
                        break;

                    case 4:
                        foreach (var s in service.ReportOnTimeStudents())
                        {
                            Console.WriteLine("Student: " + s.GetId() + " Name: " + s.Name);
                        }
                        pressEnterToContinue();
                        break;
                        break;



                    case 0:
                        inMenu = false;
                        break;

                }
            }
        }

        private String GetMenu()
        {
            return "-------Report menu--------\n\n" +
                   "1 - Students' average grades.\n" +
                   "2 - The hardest homework.\n" +
                   "3 - Examable students.\n" +
                   "4 - On time students.\n" +
                   "0 - Back.\n";
        }

        private void PrintSpaces()
        {
            for (int i = 1; i <= 100; i++)
            {
                Console.Write("\n");
            }
        }

        private void pressEnterToContinue()
        {
            Console.ReadLine();
        }
    }
}
