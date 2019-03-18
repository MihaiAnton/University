using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSApp.Domain
{
    public class Homework : IHasId<int>
    {
        private int Id;

        public Homework(int id, string description, int targetWeek, int deadlineWeek)
        {
            Id = id;
            Description = description;
            TargetWeek = targetWeek;
            DeadlineWeek = deadlineWeek;
        }

        public string Description { get; set; }
        public int TargetWeek { get; set; }
        public int DeadlineWeek { get; set; }
        

        public int GetId()
        {
            return Id;
        }

        public void SetId(int Id)
        {
            this.Id = Id;
        }

        public float ComputeGrade(float grade, int assignmentWeek)
        {
            if (assignmentWeek > DeadlineWeek)
                return 1;

            float newGrade = (float)(grade - (2.5 * (assignmentWeek - TargetWeek)));
            if (newGrade < 1)
                newGrade = 1;

            return newGrade;
        }
    }
}
