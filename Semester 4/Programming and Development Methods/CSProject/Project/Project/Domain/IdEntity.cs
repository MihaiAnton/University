using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Project.Domain
{
    interface IdEntity<T>
    {
        void SetId(T id);
        T GetId();
 
    }
}
