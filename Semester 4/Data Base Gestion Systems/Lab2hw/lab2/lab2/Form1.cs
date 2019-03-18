using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace lab2
{
    public partial class Form1 : Form
    {

        private SqlConnection sqlConnection;
        private SqlDataAdapter sqlDataAdapter;
        private DataSet dataSet, dataSet2;
        private BindingSource bindingSource;

        public Form1()
        {
            InitializeComponent();
            SetDatabaseConnection();
        }

        private void SetDatabaseConnection()
        {
            this.sqlConnection = new SqlConnection("Data Source = ANTONMIHAI-PC;" +
                                                        "Initial Catalog = OnlineShop;" +
                                                        "Integrated Security = True");
            this.sqlDataAdapter = new SqlDataAdapter();
            this.dataSet = new DataSet();
            this.dataSet2 = new DataSet();
            
            this.bindingSource = new BindingSource();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void label4_Click(object sender, EventArgs e)
        {

        }

        private void button7_Click(object sender, EventArgs e)
        {
            this.sqlDataAdapter.SelectCommand = new SqlCommand("SELECT * FROM Categories", this.sqlConnection);
            this.dataSet.Clear();
            this.sqlDataAdapter.Fill(this.dataSet);
            dataGridView1.DataSource = dataSet.Tables[0];
            bindingSource.DataSource = dataSet.Tables[0];

            //textBox1.DataBindings.Add("Text", bindingSource, "catId");
            //textBox2.DataBindings.Add("Text", bindingSource, "catName");
            // Conection between texbox and the record from the Binding Source
            //last parameter is the name of the field of the table.
        }

     

        private void button1_Click(object sender, EventArgs e)
        {
            sqlDataAdapter.InsertCommand = new SqlCommand("INSERT INTO Categories VALUES (@f, @l)", sqlConnection);
            
            sqlDataAdapter.InsertCommand.Parameters.Add("@f", SqlDbType.Int).Value = Int32.Parse(textBox1.Text);
            sqlDataAdapter.InsertCommand.Parameters.Add("@l", SqlDbType.VarChar).Value = textBox2.Text;
            sqlConnection.Open();
            sqlDataAdapter.InsertCommand.ExecuteNonQuery();
            sqlConnection.Close();

            button7_Click(null, null);
            
        }

        private void button2_Click(object sender, EventArgs e)
        {
            int x;
            
            sqlDataAdapter.UpdateCommand = new SqlCommand("Update categories set catName=@l where catId = @id", sqlConnection);
            sqlDataAdapter.UpdateCommand.Parameters.Add("@l", SqlDbType.VarChar).Value = textBox2.Text;
            sqlDataAdapter.UpdateCommand.Parameters.Add("@id", SqlDbType.Int).Value = Int32.Parse(textBox1.Text);
            sqlConnection.Open();
            x = sqlDataAdapter.UpdateCommand.ExecuteNonQuery();
            sqlConnection.Close();
            if (x >= 1)
            {
                MessageBox.Show("The record has been updated");
            }

            button7_Click(null, null);
        }

        private void button3_Click(object sender, EventArgs e)
        {
            sqlDataAdapter.UpdateCommand = new SqlCommand("Delete from categories where catId = @id",sqlConnection);
            sqlDataAdapter.UpdateCommand.Parameters.Add("@id", SqlDbType.Int).Value = Int32.Parse(textBox1.Text);

            sqlConnection.Open();

            if(sqlDataAdapter.UpdateCommand.ExecuteNonQuery() >= 1)
            {
                MessageBox.Show("The record has been deleted");
            }
            sqlConnection.Close();

            button7_Click(null, null);
        }

        private void dataGridView1_Click(object sender, EventArgs e)
        {
            
        }

        private void dataGridView1_SelectionChanged(object sender, EventArgs e)
        {

            var rowsCount = dataGridView1.SelectedRows;
            if (rowsCount.Count != 1)
            {
                return;
            }

            var row = dataGridView1.SelectedRows[0];
            int id = Int32.Parse(dataGridView1.Rows[row.Index].Cells[0].Value.ToString());
            var catName = dataGridView1.Rows[row.Index].Cells[1].Value.ToString();

            textBox1.Text = id.ToString();
            textBox2.Text = catName;
            textBox6.Text = id.ToString();

            this.sqlDataAdapter.SelectCommand = new SqlCommand("SELECT * FROM products where catId = @id", this.sqlConnection);
            this.sqlDataAdapter.SelectCommand.Parameters.Add("@id", SqlDbType.Int).Value = id;
            this.dataSet2.Clear();
            this.sqlDataAdapter.Fill(this.dataSet2);
            dataGridView2.DataSource = dataSet2.Tables[0];
            bindingSource.DataSource = dataSet.Tables[0];

           

        }

        private void button4_Click(object sender, EventArgs e)
        {
            this.sqlDataAdapter.InsertCommand = new SqlCommand("INSERT INTO products VALUES (@i, @n, @d, @c)",sqlConnection);
            this.sqlDataAdapter.InsertCommand.Parameters.Add("@i", SqlDbType.Int).Value = Int32.Parse(textBox3.Text);
            this.sqlDataAdapter.InsertCommand.Parameters.Add("@n", SqlDbType.VarChar).Value = textBox4.Text;
            this.sqlDataAdapter.InsertCommand.Parameters.Add("@d", SqlDbType.VarChar).Value = textBox5.Text;
            this.sqlDataAdapter.InsertCommand.Parameters.Add("@c", SqlDbType.Int).Value = textBox6.Text;

            sqlConnection.Open();
            sqlDataAdapter.InsertCommand.ExecuteNonQuery();
            sqlConnection.Close();
            button8_Click(null, null);
        }

        private void button5_Click(object sender, EventArgs e)
        {
            sqlDataAdapter.UpdateCommand = new SqlCommand("UPDATE products SET pName = @n, pDescription = @d, catId = @i WHERE pId = @id", sqlConnection);
            sqlDataAdapter.UpdateCommand.Parameters.Add("@n", SqlDbType.VarChar).Value = textBox4.Text;
            sqlDataAdapter.UpdateCommand.Parameters.Add("@d", SqlDbType.VarChar).Value = textBox5.Text;
            sqlDataAdapter.UpdateCommand.Parameters.Add("@i", SqlDbType.Int).Value = Int32.Parse(textBox6.Text);
            sqlDataAdapter.UpdateCommand.Parameters.Add("@id", SqlDbType.Int).Value = Int32.Parse(textBox3.Text);

            sqlConnection.Open();
            sqlDataAdapter.UpdateCommand.ExecuteNonQuery();
            sqlConnection.Close();
            button8_Click(null, null);
        }

        private void button6_Click(object sender, EventArgs e)
        {
            sqlDataAdapter.DeleteCommand = new SqlCommand("Delete from products where pId = @i",sqlConnection);
            sqlDataAdapter.DeleteCommand.Parameters.Add("@i", SqlDbType.Int).Value = Int32.Parse(textBox3.Text);
            sqlConnection.Open();
            sqlDataAdapter.DeleteCommand.ExecuteNonQuery();
            sqlConnection.Close();
            button8_Click(null, null);
        }

        private void dataGridView2_SelectionChanged(object sender, EventArgs e)
        {
            var rowsCount = dataGridView2.SelectedRows;
            if (rowsCount.Count != 1)
            {
                return;
            }

            var row = dataGridView2.SelectedRows[0];
            int id = Int32.Parse(dataGridView1.Rows[row.Index].Cells[0].Value.ToString());
            var pName = dataGridView2.Rows[row.Index].Cells[1].Value.ToString();
            var pDesc = dataGridView2.Rows[row.Index].Cells[2].Value.ToString();
            var catId = dataGridView2.Rows[row.Index].Cells[3].Value.ToString();

            textBox3.Text = id.ToString();
            textBox4.Text = pName.ToString();
            textBox5.Text = pDesc.ToString();
            textBox6.Text = catId.ToString();



        }

        private void button8_Click(object sender, EventArgs e)
        {
            this.sqlDataAdapter.SelectCommand = new SqlCommand("SELECT * FROM products", this.sqlConnection);
            this.dataSet2.Clear();
            this.sqlDataAdapter.Fill(this.dataSet2);
            dataGridView2.DataSource = dataSet2.Tables[0];
            bindingSource.DataSource = dataSet2.Tables[0];

        }
    }
}
