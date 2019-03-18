using CSApp.Domain;
using CSApp.Validators;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSApp.Repository
{
    class GradeTxtRepository : GenericTxtRepository<string, Grade>
    {
        public GradeTxtRepository(IValidator<Grade> validator, string fileName) : base(validator, fileName)
        {
        }

        private static string GradeToString(Grade g)
        {
            return g.StudentId + "|" + g.HomeworkId + "|" + g.AssignmentWeek + "|" + g.GradeValue + "|" + g.Feedback;
        }

        private static Grade StringToGrade(string line)
        {
            string[] s = line.Split('|');
            return new Grade(s[0], Int32.Parse(s[1]), Int32.Parse(s[2]), float.Parse(s[3]), s[4]);
        }

        public override void SetDelegates()
        {
            this.EntityToString = new EntityToString<Grade>(GradeToString);
            this.StringToEntity = new StringToEntity<Grade>(StringToGrade);            
        }
    }
}
