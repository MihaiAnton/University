using CSApp.Domain;
using CSApp.Validators;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSApp.Repository
{
    public class StudentTxtRepository : GenericTxtRepository<String, Student>
    {
        public StudentTxtRepository(IValidator<Student> validator, string fileName) : base(validator, fileName) { }

        private static Student StringToStudent(String data)
        {
            String[] splits = data.Split('|');
            return new Student(splits[0], splits[1], Int32.Parse(splits[2]), splits[3], splits[4]);
        }

        private static String StudentToString(Student student)
        {
            return student.GetId() + "|" + student.Name + "|" + student.Group + "|" + student.Mail + "|" + student.Teacher;
        }

        public override void SetDelegates()
        {
            base.StringToEntity = new StringToEntity<Student>(StringToStudent);
            base.EntityToString = new EntityToString<Student>(StudentToString);
        }
    }
}
