using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSApp.Domain
{
    public class Student : IHasId<string>
    {
        
        public string id;
        public string Name { get; set; }
        public int Group { get; set; }
        public string Mail { get; set; }
        public string Teacher { get; set; }


        public Student(string id, string name, int group, string mail, string teacher)
        {
            this.id = id;
            this.Name = name;
            this.Group = group;
            this.Mail = mail;
            this.Teacher = teacher;
        }
        
        public string GetId()
        {
            return this.id;
        }

        public void SetId(string Id)
        {
            this.id = id;
        }
    }
}
