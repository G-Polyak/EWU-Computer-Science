/* 
     Name: George Polyak
     Class Description: Provides GUI functionality for displaying people in the database and
     allowing the selection of records to create a list of chosen recipients for the e-mail
*/

using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SQLite;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace HolidayMailer
{
    /// <summary>
    /// Interaction logic for ChooseWindow.xaml
    /// </summary>
    public partial class ChooseWindow : Window
    {

        public List<string> chosenRecipients;
        private DataTable chooseTable;
        private SQLiteConnection sqlite_conn;
        private SQLiteCommand sqlite_cmd;
        private SQLiteDataReader sqlite_reader;

        public ChooseWindow()
        {
            chooseTable = new DataTable();
            InitializeComponent();
            sqlite_conn = new SQLiteConnection("Data Source=peopleAndHolidays.db;Version=3;New=True;Compress=True;");
            RefreshChooseWindow();
        }

        public void RefreshChooseWindow()
        {
            lastYearCheckBox.IsChecked = false;
            selectAllCheckBox.IsChecked = false;
            sqlite_conn.Open();
            sqlite_cmd = sqlite_conn.CreateCommand();
            sqlite_cmd.CommandText = "SELECT LastName, FirstName, EmailAddress, EmailLastYear FROM People ORDER BY LastName ASC;";
            sqlite_reader = sqlite_cmd.ExecuteReader();
            chooseTable = new DataTable();
            chooseTable.Load(sqlite_reader);
            
            DataColumn dc = new DataColumn("ischecked")
            {
                DataType = typeof(bool),
                AllowDBNull = false,
                DefaultValue = false
            };
            chooseTable.Columns.Add(dc);
            chooseGrid.ItemsSource = chooseTable.DefaultView;
            sqlite_reader.Dispose();
            sqlite_conn.Close();
        }

        private void ChooseWindow_Close(object sender, System.ComponentModel.CancelEventArgs e)
        {
            e.Cancel = true;
            Visibility = Visibility.Hidden;
        }

        private void SubmitChosenBtn_Click(object sender, RoutedEventArgs e)
        {
            chosenRecipients = new List<string>();
            foreach (DataRow dr in chooseTable.Rows)
            {
                if ((bool) dr["ischecked"])
                {
                    chosenRecipients.Add(dr["EmailAddress"].ToString());
                }
            }
            Visibility = Visibility.Hidden;
        }

        private void LastYearCheckBox_Checked(object sender, RoutedEventArgs e)
        {
            selectAllCheckBox.IsChecked = false;
            foreach (DataRow dr in chooseTable.Rows)
            {
                if (dr["EmailLastYear"].ToString() == "YES")
                {
                    dr["ischecked"] = true;
                } else
                {
                    dr["ischecked"] = false;
                }
            }
        }

        private void LastYearCheckBox_Unchecked(object sender, RoutedEventArgs e)
        {
            foreach (DataRow dr in chooseTable.Rows)
            {
                if (dr["EmailLastYear"].ToString() == "YES")
                {
                    dr["ischecked"] = false;
                }
            }
        }

        private void SelectAllCheckBox_Checked(object sender, RoutedEventArgs e)
        {
            lastYearCheckBox.IsChecked = false;
            foreach (DataRow dr in chooseTable.Rows)
            {
                dr["ischecked"] = true;
            }
        }

        private void SelectAllCheckBox_Unchecked(object sender, RoutedEventArgs e)
        {
            foreach (DataRow dr in chooseTable.Rows)
            {
                dr["ischecked"] = false;
            }
        }

        private void CheckedRecord_Checked(object sender, RoutedEventArgs e)
        {
            ((CheckBox)sender).GetBindingExpression(CheckBox.IsCheckedProperty).UpdateSource();
        }

        private void CheckedRecord_Unchecked(object sender, RoutedEventArgs e)
        {
            ((CheckBox)sender).GetBindingExpression(CheckBox.IsCheckedProperty).UpdateSource();
        }
    }
}
