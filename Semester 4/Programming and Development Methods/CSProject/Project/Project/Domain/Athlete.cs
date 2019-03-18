using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Project.Domain
{
    class Athlete : IdEntity<int>
    {
        public Athlete(int id, string name, DateTime birthDate)
        {
            this.id = id;
            this.name = name;
            this.birthDate = birthDate;
        }

        public int id { get; set; }
        public String name { get; set; }
        public DateTime birthDate { get; set; }



        public int GetId()
        {
            return id;
        }

        public void SetId(int id)
        {
            this.id = id;
        }
    }
}
