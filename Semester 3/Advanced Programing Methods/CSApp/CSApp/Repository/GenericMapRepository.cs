using CSApp.Domain;
using CSApp.Exceptions;
using CSApp.Validators;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSApp.Repository
{
    public class GenericMapRepository<ID, E> : ICRUDRepository<ID, E> where E : class,IHasId<ID>
    {
        protected Dictionary<ID, E> dictionary;
        protected IValidator<E> validator;

        public GenericMapRepository(IValidator<E> validator)
        {
            this.dictionary = new Dictionary<ID, E>();
            this.validator = validator;
        }

        public virtual E Delete(ID id)
        {
            if(id == null)
            {
                throw new InvalidParamException("Null id in delete");
            }

            if (!dictionary.ContainsKey(id))
            {
                return null;
            }
            else
            {
                E removed = dictionary[id];
                dictionary.Remove(id);
                return removed;
            }
        }

        public IEnumerable<E> FindAll()
        {
            return this.dictionary.Values;
        }

        public E FindOne(ID id)
        {
            if (id == null)
            {
                throw new InvalidParamException("Null id in findOne");
            }

            if (!dictionary.ContainsKey(id))
            {
                return null;
            }
            else
            {
                return dictionary[id];
            }
        }

        public virtual E Save(E entity)
        {
            if(entity == null)
            {
                throw new InvalidParamException("Null entity in save");
            }

            if (dictionary.ContainsKey(entity.GetId()))
            {
                return entity;
            }
            else
            {
                dictionary.Add(entity.GetId(), entity);
                return null;
            }
        }

        public virtual E Update(E entity)
        {
            if(entity == null)
            {
                throw new InvalidParamException("Null entity in update");
            }

            if (!dictionary.ContainsKey(entity.GetId()))
            {
                return entity;
            }
            else
            {
                dictionary[entity.GetId()] = entity;
                return null;
            }

        }
    }
}
