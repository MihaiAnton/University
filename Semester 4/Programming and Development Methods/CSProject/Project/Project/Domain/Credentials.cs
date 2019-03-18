using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Project.Domain
{
    class Credentials : IdEntity<int>
    {
        public Credentials(int userId, string password)
        {
            this.userId = userId;
            this.password = password;
        }

        public int userId { get; set; }
        public String password { get; set; }


        public int GetId()
        {
            return userId;
        }

        public void SetId(int id)
        {
            this.userId = id;
        }
    }
}
