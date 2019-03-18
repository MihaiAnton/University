using CSApp.Domain;
using CSApp.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSApp.UI
{
    class GradingMenu
    {
        private TeacherService service;

        public GradingMenu(TeacherService service)
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

                        Console.Write("Student Id: ");
                        String sid = Console.ReadLine();

                        Console.Write("Homework Id: ");
                        int hid = Int32.Parse(Console.ReadLine());

                        Console.Write("Week: ");
                        int week = Int32.Parse(Console.ReadLine());

                        Console.Write("Grade: ");
                        float grade = float.Parse(Console.ReadLine());

                        Console.Write("Feedback: ");
                        String fback = Console.ReadLine();

                        Console.Write(service.GradeStudent(sid, hid, week, grade, fback));
                    
                        break;

                    case 2:
                        Console.Write("Homework Id: ");
                        hid = Int32.Parse(Console.ReadLine());

                        Console.WriteLine(service.DelayHomework(hid));
                        break;

                    case 0:
                        inMenu = false;
                        break;

                }
            }
        }

      

        private String GetMenu()
        {
            return "-------Grading menu--------\n\n" +
                   "1 - Grade Student\n" +
                   "2 - Delay Homework\n" +
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
            Console.Read();
        }
    }
}
