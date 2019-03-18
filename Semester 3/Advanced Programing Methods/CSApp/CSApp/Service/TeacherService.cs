using CSApp.Domain;
using CSApp.Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSApp.Service
{
    public class TeacherService
    {
        private GenericMapRepository<String, Student> StudentRepo;
        private GenericMapRepository<int, Homework> HomeworkRepo;
        private GenericMapRepository<String, Grade> GradeRepo;

        public TeacherService(GenericMapRepository<string, Student> studentRepo,
                              GenericMapRepository<int, Homework> homeworkRepo,
                              GenericMapRepository<string, Grade> gradeRepo)
        {
            StudentRepo = studentRepo;
            HomeworkRepo = homeworkRepo;
            GradeRepo = gradeRepo;
        }

        public String PrintTest()
        {
            string rez = "";
            foreach (Student s in StudentRepo.FindAll())
            {
                rez += s.Name + "\n";
            }

            foreach (Homework h in HomeworkRepo.FindAll())
            {
                rez += h.Description + "\n";
            }

            foreach (Grade g in GradeRepo.FindAll())
            {
                rez += g.GetId() + "\n";
            }

            return rez;
        }

        public String AddStudent(Student student)
        {
            try
            {

                if (this.StudentRepo.Save(student) == null)
                {
                    return "Student saved.";
                }
                else
                {
                    return "Student already exists.";
                }
            }
            catch (Exception e)
            {
                return e.Message;
            }
        }

        public String UpdateStudent(Student student)
        {
            try
            {
               
                if (this.StudentRepo.Update(student) == null)
                {
                    return "Student updated.";
                }
                else
                {
                    return "Student not found.";
                }
            }
            catch (Exception e)
            {
                return e.Message;
            }
        }

        public String DeleteStudent(String id)
        {
            try
            { 
                if (this.StudentRepo.Delete(id) != null)
                {
                    return "Student " + id + " deleted.";
                }
                else
                {
                    return "Student not found.";
                }
            }
            catch (Exception e)
            {
                return e.Message;
            }
        }

        public String AddHomework(Homework homework)
        {
            try
            {
                if(this.HomeworkRepo.Save(homework) == null) {
                    return "Homework added.";
                }
                else
                {
                    return "Existing homework.";
                }

            }
            catch(Exception e)
            {
                return e.Message;
            }
        }

        public String GradeStudent(String StudentId, int HomeworkId, int week, float grade, string feedback)
        {
            try
            {
                Student s = StudentRepo.FindOne(StudentId);
                if(s == null)
                {
                    return "Student not found.";
                }

                Homework h = HomeworkRepo.FindOne(HomeworkId);
                if(h == null)
                {
                    return "Homework not found.";
                }

                float finalGrade = h.ComputeGrade(grade, week);
                GradeRepo.Save(new Grade(StudentId, HomeworkId, week, finalGrade, feedback));
                return "Student graded " + finalGrade + ".";
            }
            catch(Exception e)
            {
                return e.Message;
            }
        }

        public String DelayHomework(int Hid)
        {
            Homework h = HomeworkRepo.FindOne(Hid);
            if(h == null)
            {
                return "Homework not found.";
            }
            else
            {
                if(h.TargetWeek < h.DeadlineWeek)
                {
                    h.TargetWeek++;
                    HomeworkRepo.Update(h);

                    List<String> hids = new List<string>();

                    foreach(Grade g in GradeRepo.FindAll())
                    {
                        if(g.HomeworkId == Hid)
                        {
                            hids.Add(g.GetId());
                        }
                    }

                    foreach(String id in hids)
                    {
                        Grade g = GradeRepo.FindOne(id);
                        g.GradeValue = (float)(g.GradeValue + 2.5);
                        if (g.GradeValue > 10)
                            g.GradeValue = 10;

                        GradeRepo.Update(g);
                    }

                    return "Homework delayed";
                }
                else
                {
                    return "Can't postpone homework";
                }
            }

        }


        ////////////////////Filter methods
        public List<Student> FilterStudentById(String id)
        {
            var filtered = from student in this.StudentRepo.FindAll()
                           where student.GetId().Equals(id)
                           select student;

            return filtered.ToList();
        }
        //adriana@cs.ubbcluj.ro

        public List<Student> FilterStudentByGroup(int groupId)
        {

            var filtered = from student in this.StudentRepo.FindAll()
                           where student.Group == groupId
                           select student;
            return filtered.ToList();
        }

        public List<Homework> FilterHomeworkById(int id)
        {
            var filtered = from hw in this.HomeworkRepo.FindAll()
                           where hw.GetId() == id
                           select hw;
            return filtered.ToList();
        }

        public List<Grade> FilterGradesByStudentId(String studId)
        {
            var filtered = from gr in this.GradeRepo.FindAll()
                           where gr.StudentId.Equals(studId)
                           select gr;
            return filtered.ToList();
        }

        public List<Grade> FilterGradesByHomeworkId(int hid)
        {
            var filtered = from gr in this.GradeRepo.FindAll()
                           where gr.HomeworkId == hid
                           select gr;
            return filtered.ToList();
        }


        ///////////////////Report methods
        
        public List<KeyValuePair<Student, double>> ReportStudentsAvgGrades()
        {
            List<KeyValuePair<Student, double>> list = new List<KeyValuePair<Student, double>>();
            HashSet<String> ids = new HashSet<string>();
            foreach(Grade g in GradeRepo.FindAll())
            {
                ids.Add(g.StudentId);
            }

            foreach(String id in ids)
            {
                double sum = 0;
                int count = 0;

                foreach(Grade g in GradeRepo.FindAll())
                {
                    if (g.StudentId.Equals(id))
                    {
                        Homework h = HomeworkRepo.FindOne(g.HomeworkId);
                        int ponder = h.DeadlineWeek - h.TargetWeek + 1;
                        sum += g.GradeValue * ponder;
                        count += ponder;
                    }
                }

                list.Add(new KeyValuePair<Student, double>(StudentRepo.FindOne(id),sum/count));
               
            }

            return list;  

        }
        

        public Homework ReportHardestHomework()
        {
            HashSet<int> set = new HashSet<int>();

            foreach(Grade g in GradeRepo.FindAll())
            {
                set.Add(g.HomeworkId);
            }

            int minHid = -1;
            double minAvg = 0;

            foreach(int hid in set)
            {
                double sum = 0;
                int count = 0;

                foreach(Grade g in GradeRepo.FindAll())
                {
                    if(g.HomeworkId == hid)
                    {
                        Homework h = HomeworkRepo.FindOne(hid);
                        sum += g.GradeValue * (h.DeadlineWeek - h.TargetWeek + 1);
                        count += (h.DeadlineWeek - h.TargetWeek + 1);
                    }
                }

                if(minHid == -1 || (sum/count) < minAvg)
                {
                    minHid = hid;
                    minAvg = (sum / count);
                }


            }
            return HomeworkRepo.FindOne(minHid);
        }

        public List<Student> ReportExamAbleStudents()
        {
            List<Student> res = new List<Student>();
            HashSet<String> ids = new HashSet<string>();
            foreach (Grade g in GradeRepo.FindAll())
            {
                ids.Add(g.StudentId);
            }

            foreach (String id in ids)
            {
                double sum = 0;
                int count = 0;

                foreach (Grade g in GradeRepo.FindAll())
                {
                    if (g.StudentId.Equals(id))
                    {
                        Homework h = HomeworkRepo.FindOne(g.HomeworkId);
                        int ponder = h.DeadlineWeek - h.TargetWeek + 1;
                        sum += g.GradeValue * ponder;
                        count += ponder;
                    }
                }

                if (sum / count > 4)
                {
                    res.Add(StudentRepo.FindOne(id));
                }

            }

            return res;
        }

        public List<Student> ReportOnTimeStudents()
        {
            List<Student> res = new List<Student>();
            HashSet<String> ids = new HashSet<string>();
            HashSet<String> ids2 = new HashSet<string>();
            foreach (Grade g in GradeRepo.FindAll())
            {
                ids.Add(g.StudentId);
                ids2.Add(g.StudentId);
            }



 
            foreach (Grade g in GradeRepo.FindAll())
            {
                if (ids2.Contains(g.StudentId))
                {
                    if(g.AssignmentWeek > HomeworkRepo.FindOne(g.HomeworkId).TargetWeek)
                    {
                        ids2.Remove(g.StudentId);
                    }
                }
            }



            foreach(String id in ids2)
            {
                res.Add(StudentRepo.FindOne(id));
            }
            return res;          

        }
    }
}
