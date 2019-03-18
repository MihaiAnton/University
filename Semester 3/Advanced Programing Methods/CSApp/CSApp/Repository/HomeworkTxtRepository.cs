using CSApp.Domain;
using CSApp.Validators;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSApp.Repository
{
    public class HomeworkTxtRepository : GenericTxtRepository<int, Homework>
    {
        public HomeworkTxtRepository(HomeworkValidator validator, string fileName):base(validator, fileName) { }


        private static Homework StringToHomework(string data)
        {
            string[] splits = data.Split('|');
            return new Homework(Int32.Parse(splits[0]), splits[1],
                                Int32.Parse(splits[2]), Int32.Parse(splits[3]));
        }

        private static string HomeworkToString(Homework h)
        {
            return h.GetId() + "|" + h.Description + "|" + h.TargetWeek + "|" + h.DeadlineWeek;
        }

        public override void SetDelegates()
        {
            this.EntityToString = new EntityToString<Homework>(HomeworkToString);
            this.StringToEntity = new StringToEntity<Homework>(StringToHomework);
        }
    }
}
