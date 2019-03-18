using CSApp.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace CSApp.Repository
{
    public interface ICRUDRepository<ID, E>
    {
        /// <summary>
        ///     Searches for a entity of the givven id.
        /// </summary>
        /// <param name="id"></param>
        /// <returns>
        ///     the entity with the specified id
        ///     or null - if there is no entity with the given id
        /// </returns>
        E FindOne(ID id);

        /// <summary>
        ///     Stores the entity
        /// </summary>
        /// <param name="entity"></param>
        /// <returns>
        ///     null- if the given entity is saved
        ///     otherwise returns the entity(id already exists)
        /// </returns>
        E Save(E entity);


        /// <summary>
        ///     Deletes the entity woth the givven id, if found;
        /// </summary>
        /// <param name="id"></param>
        /// <returns>
        ///     the removed entity or null if there is no entity with the given id
        /// </returns>
        E Delete(ID id);

        /// <summary>
        ///     Updates the entity if found;
        /// </summary>
        /// <param name="id"></param>
        /// <returns>
        ///     null - if the entity is updated,
        ///     otherwise returns the entity - (e.g id does not exist).
        /// </returns>
        E Update(E entity);

        /// <summary>
        ///     Returns the list of entities
        /// </summary>
        /// <returns></returns>
        IEnumerable<E> FindAll();

    }
}
