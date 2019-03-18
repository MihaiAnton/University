using CSApp.Domain;
using CSApp.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSApp.UI
{
    class StudentMenu
    {
        private TeacherService service;

        public StudentMenu(TeacherService service)
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
                        Student s = ReadStudent();
                        if(s == null)
                        {
                            Console.Write("Incorrect data.");
                        }
                        else
                        {
                            Console.Write(service.AddStudent(s) + "\n");
                        }
                        break;

                    case 2:
                        s = ReadStudent();
                        if (s == null)
                        {
                            Console.Write("Incorrect data.");
                        }
                        else
                        {
                            Console.Write(service.UpdateStudent(s) + "\n");
                        }
                        break;

                    case 3:
                        Console.Write("Id: ");
                        string id = Console.ReadLine();
                        if (id == null)
                        {
                            Console.Write("Incorrect data.");
                        }
                        else
                        {
                            Console.Write(service.DeleteStudent(id) + "\n");
                        }
                        break;

                    case 0:
                        inMenu = false;
                        break;

                }
            }
        }

        private Student ReadStudent()
        {
            try
            {
                Console.WriteLine("Id: ");
                string id = Console.ReadLine();

                Console.WriteLine("Name: ");
                string name = Console.ReadLine();

                Console.WriteLine("Group: ");
                int group = Int32.Parse(Console.ReadLine());

                Console.WriteLine("Mail: ");
                string mail = Console.ReadLine();

                Console.WriteLine("Teacher: ");
                string teacher = Console.ReadLine();

                return new Student(id, name, group, mail, teacher);

            }
            catch(Exception e)
            {
                return null;
            }
        }

        private String GetMenu()
        {
            return "-------Student menu--------\n\n" +
                   "1 - Add Student\n" +
                   "2 - Update Student\n" +
                   "3 - Remove Student.\n" +
                   "4 - Back.\n";
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
