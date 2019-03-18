using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSApp.Domain
{
    public interface IHasId<T>
    {
        void SetId(T Id);
        T GetId();
    }
}
