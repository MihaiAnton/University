using CSApp.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSApp.UI
{
    public class UI
    {
        private TeacherService service;
        private StudentMenu studentMenu;
        private HomeworkMenu homeworkMenu;
        private GradingMenu gradingMenu;
        private FilterMenu filterMenu;
        private ReportMenu reportMenu;

        public UI(TeacherService service)
        {
            this.service = service;
            this.studentMenu = new StudentMenu(service);
            this.homeworkMenu = new HomeworkMenu(service);
            this.gradingMenu = new GradingMenu(service);
            this.filterMenu = new FilterMenu(service);
            this.reportMenu = new ReportMenu(service);
        }

        public void Run()
        {
            bool inMenu = true;

            while (inMenu)
            {
                PrintSpaces();
                Console.Write(GetMenu());
                int command = Int32.Parse(Console.ReadLine());


                switch (command)
                {
                    case 1:
                        StudentMenu();
                        break;

                    case 2:
                        HomeworkMenu();
                        break;

                    case 3:
                        GradingMenu();
                        break;

                    case 4:
                        FilterMenu();
                        break;

                    case 5:
                        ReportMenu();
                        break;

                    case 0:
                        goto final;

                }
            }
        final:;
        }

        private String GetMenu()
        {
            return "-------Main menu--------\n\n" +
                   "1 - Student Menu\n" +
                   "2 - Homework Menu\n" +
                   "3 - Grading Menu\n" + 
                   "4 - Filter Menu\n" +
                   "5 - Report Menu\n" +
                   "0 - Exit.\n";
        }

        private void StudentMenu()
        {
            this.studentMenu.Run();
        }

        private void HomeworkMenu()
        {
            this.homeworkMenu.Run();
        }

        private void GradingMenu()
        {
            this.gradingMenu.Run();
        }

        private void FilterMenu()
        {
            this.filterMenu.Run();
        }

        private void ReportMenu()
        {
            this.reportMenu.Run();
        }

        private void PrintSpaces()
        {
            for(int i = 1; i <= 100; i++)
            {
                Console.Write("\n");
            }
        }

       

    }
}
