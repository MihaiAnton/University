using CSApp.Domain;
using CSApp.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSApp.UI
{
    class FilterMenu
    {
        private TeacherService service;

        public FilterMenu(TeacherService service)
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
                        Console.Write("Student id: ");
                        String id = Console.ReadLine();
                        foreach (Student s in service.FilterStudentById(id))
                        {
                            Console.WriteLine(s.GetId() + " " + s.Name);
                        }
                        pressEnterToContinue();
                        break;

                    case 2:
                        Console.Write("Student group: ");
                        int group = Int32.Parse(Console.ReadLine());
                        foreach(Student s in service.FilterStudentByGroup(group))
                        {
                            Console.WriteLine(s.GetId() + " " + s.Name);
                        }
                        pressEnterToContinue();
                        break;


                    case 3:
                        Console.Write("Homework id: ");
                        int hid = Int32.Parse(Console.ReadLine());
                        foreach(Homework h in service.FilterHomeworkById(hid))
                        {
                            Console.WriteLine(h.Description + " Target week: " + h.TargetWeek);
                        }
                        pressEnterToContinue();
                        break;


                    case 4:
                        Console.Write("Student id: ");
                        id = Console.ReadLine();
                        foreach(Grade g in service.FilterGradesByStudentId(id))
                        {
                            Console.WriteLine("Homework: " + g.HomeworkId + " Grade: " + g.GradeValue);
                        }
                        pressEnterToContinue();
                        break;


                    case 5:
                        Console.Write("Homework id: ");
                        hid = Int32.Parse(Console.ReadLine());
                        foreach(Grade g in service.FilterGradesByHomeworkId(hid))
                        {
                            Console.WriteLine("Student: " + g.StudentId + " Grade: " + g.GradeValue);
                        }
                        pressEnterToContinue();
                        break;


                    case 0:
                        inMenu = false;
                        break;

                }
            }
        }

        private String GetMenu()
        {
            return "-------Filter menu--------\n\n" +
                   "1 - Filter students by id.\n" +
                   "2 - Filter students by group.\n" +
                   "3 - Filter homework by id.\n" +
                   "4 - Filter grades by student.\n" + 
                   "5 - Filter grades by homework.\n" +
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
