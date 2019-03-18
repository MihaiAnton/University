using System;
using System.Data;
using System.Collections.Generic;
using MySql.Data.MySqlClient;
namespace ConnectionUtils
{
	
	public class MySqlConnectionFactory
	{
		public MySqlConnection CreateConnection(IDictionary<string,string> props)
		{
			//MySql Connection
			String connectionString = "Database=mppproject;" +
										"Data Source=localhost;" +
										"User id=root;" +
										"Password=password;";
			//String connectionString = props["ConnectionString"];
			Console.WriteLine("MySql ---se deschide o conexiune la  ... {0}", connectionString);
			
			return new MySqlConnection(connectionString);

	
		}
	}
}
