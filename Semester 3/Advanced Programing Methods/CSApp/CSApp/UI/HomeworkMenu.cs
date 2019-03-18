using CSApp.Domain;
using CSApp.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSApp.UI
{
    class HomeworkMenu
    {
        private TeacherService service;

        public HomeworkMenu(TeacherService service)
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
                        Homework h = ReadHomework();
                        if (h == null)
                        {
                            Console.Write("Incorrect data.");
                        }
                        else
                        {
                            Console.Write(service.AddHomework(h) + "\n");
                        }
                        break;

                    case 0:
                        inMenu = false;
                        break;

                }
            }
        }

        private Homework ReadHomework()
        {
            try
            {
                Console.Write("Id: ");
                int id = Int32.Parse(Console.ReadLine());

                Console.Write("Description: ");
                string desc = Console.ReadLine();

                Console.Write("Assignment Week: ");
                int aw = Int32.Parse(Console.ReadLine());

                Console.Write("Deadline Week: ");
                int dw = Int32.Parse(Console.ReadLine());

                return new Homework(id, desc, aw, dw);
            }
            catch(Exception e)
            {
                return null;
            }
        }

        private String GetMenu()
        {
            return "-------Homework menu--------\n\n" +
                   "1 - Add Homework\n" +
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
