using CSApp.Domain;
using CSApp.Validators;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSApp.Repository
{
    public delegate string EntityToString<E>(E entity);
    public delegate E StringToEntity<E>(string data);

    public abstract class GenericTxtRepository<ID, E> : GenericMapStorageRepository<ID, E> where E:class, IHasId<ID>
    {
        public GenericTxtRepository(IValidator<E> validator,string fileName) : base(validator, fileName) { }
      

        public override void LoadAll()
        {
            string[] lines = System.IO.File.ReadAllLines(base.FileName);
            foreach(string line in lines)
            {
                try
                {
                    E e = StringToEntity(line);
                    base.Save(e);
                }
                catch (Exception err) { };
                
            }
        }

        public StringToEntity<E> StringToEntity { get;  set; }

        public override void StoreAll()
        {
            string text = "";

            foreach (E entity in base.FindAll())
            {
                text = text + EntityToString(entity) + "\n";
            }

            System.IO.File.WriteAllText(base.FileName, text);

        }

        public EntityToString<E> EntityToString { get; set; }
    }
}
