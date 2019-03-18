using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSApp.Domain
{
    public class Grade : IHasId<string>
    {
        private string Id;

        public Grade(string studentId, int homeworkId, int assignmentWeek, float gradeValue, string feedback)
        {
            this.SetId(studentId + homeworkId);
            StudentId = studentId;
            HomeworkId = homeworkId;
            AssignmentWeek = assignmentWeek;
            GradeValue = gradeValue;
            Feedback = feedback;
        }

        public string StudentId { get; set; }
        public int HomeworkId { get; set; }
        public int AssignmentWeek { get; set; }
        public float GradeValue { get; set; }
        public string Feedback { get; set; }


        public string GetId()
        {
            return this.Id;
        }

        public void SetId(string Id)
        {
            this.Id = Id;
        }
    }
}
