using MySql.Data.MySqlClient;
using Project.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Project.Repository
{
    abstract class DataBaseRepo<T, ID> : ICRUDRepo<T, ID> where T : class, IdEntity<ID>
    {
        protected DataBaseRepo(string tableName, MySqlConnection connection)
        {
            this.tableName = tableName;
            this.connection = connection;
        }

        public abstract String GetInsertString(T entity);
        public abstract String GetSelectString(ID id);
        public abstract String GetUpdateString(T entity);
        public abstract String GetDeleteString(ID id);

        private String tableName { get; set; }
        
        private MySqlConnection connection { get; set; }



        public T delete(ID id)
        {
            String query = GetDeleteString(id);
            T elem = findOne(id);
            if (elem == null)
                return null;

            connection.Open();
            MySqlCommand command = new MySqlCommand(query, connection);
            command.ExecuteNonQuery();
            connection.Close();

            return elem;
        }

        public List<T> findAll()
        {
            throw new NotImplementedException();
        }

        public T findOne(ID id)
        {
            throw new NotImplementedException();
        }

        public T save(T entity)
        {
            String query = 
            throw new NotImplementedException();
        }

        public T update(T entity)
        {
            throw new NotImplementedException();
        }
    }
}
