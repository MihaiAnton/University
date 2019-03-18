using ConnectionUtils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MySql.Data.MySqlClient;
using System.Data;

namespace Project
{
    class Project
    {
        static void Main(string[] args)
        {
            MySqlConnectionFactory factory = new MySqlConnectionFactory();
            MySqlConnection connection = factory.CreateConnection(null);
            connection.Open();
            MySqlCommand command = new MySqlCommand("select * from athletes", connection);
            MySqlDataAdapter dataAdapter = new MySqlDataAdapter(command);
            DataSet dataSet = new DataSet();


            dataAdapter.Fill(dataSet);
            var dataR = command.ExecuteReader();
            if (dataR.Read())
            {
                string name = dataR.GetString(1);
                Console.Write(name);
            }


            Console.Write(connection);
            Console.Read();
        }
    }
}
