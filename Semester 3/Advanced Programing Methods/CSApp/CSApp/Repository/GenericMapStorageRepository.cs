using CSApp.Domain;
using CSApp.Validators;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSApp.Repository
{
    public abstract class GenericMapStorageRepository<ID, E> : GenericMapRepository<ID, E> where E:class, IHasId<ID>
    {
        /// <summary>
        ///     Constructor with a generic validator.
        /// </summary>
        /// <param name="validator"></param>

        public string FileName { get; }

        public GenericMapStorageRepository(IValidator<E> validator, string fileName) : base(validator)
        {
            this.FileName = fileName;
            SetDelegates();
            LoadAll();
        }

        public abstract void StoreAll();

        public abstract void LoadAll();

        public abstract void SetDelegates();


        public override E Delete(ID id)
        {
            var retVal =  base.Delete(id);
            StoreAll();
            return retVal;
        }

        public override E Save(E entity)
        {
            var retVal = base.Save(entity);
            StoreAll();
            return retVal;
        }

        public override E Update(E entity)
        {
            var retVal = base.Update(entity);
            StoreAll();
            return retVal;
        }

    }
}
