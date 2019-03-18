using Project.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Project.Repository
{
    interface ICRUDRepo<T,ID> where T : IdEntity<ID>
    {
        T save(T entity);
        T update(T entity);
        T delete(ID id);
        T findOne(ID id);

        List<T> findAll();
    }
}
