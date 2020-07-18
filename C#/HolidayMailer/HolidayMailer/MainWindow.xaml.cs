/* 
     Name: George Polyak
     Class Description: Provides GUI functionality for creating/displaying the People and Holidays tables
     in the database, opening the AddPerson, AddHoliday, and ChooseRecipients windows to gather info, 
     adding gathered info to the database if needed, automatically e-mailing correct recipients if 
     conditions are met on startup, and sending an entered e-mail to all chosen recipients
*/

using System;
using System.Collections.Generic;
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
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Net;
using System.Net.Mail;
using System.IO;
using System.Data.SQLite;
using System.Data;
using Microsoft.Win32;

namespace HolidayMailer
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {

        private ChooseWindow chooseWindow;
        private AddPersonWindow addPersonWindow;
        private AddHolidayWindow addHolidayWindow;
        private bool attachment;
        private List<string> recipients;
        private Attachment attachmentObj;
        private SmtpClient client;
        private string myPwd = "198732465";
        private string myEmail = "georgiepolyak@gmail.com";
        private DataTable peopleDT;
        private DataTable holidaysDT;
        private SQLiteConnection sqlite_conn;
        private SQLiteCommand sqlite_cmd;
        private SQLiteDataReader sqlite_reader;
        private SQLiteDataReader tempReader;

        public MainWindow()
        {
            recipients = new List<string>();
            attachment = false;
            client = new SmtpClient("smtp.gmail.com", 587)
            {
                Credentials = new NetworkCredential(myEmail, myPwd),
                EnableSsl = true
            };
            SetupDBs();
            chooseWindow = new ChooseWindow();
            addPersonWindow = new AddPersonWindow();
            addHolidayWindow = new AddHolidayWindow();
            InitializeComponent();
            if (!File.Exists("autoEmails.txt"))
            {
                autoEmail.IsChecked = false;
            }
            else
            {
                using (StreamReader sr = File.OpenText("autoEmails.txt"))
                {
                    string s = sr.ReadLine();
                    if (s == "YES")
                    {
                        autoEmail.IsChecked = true;
                        SendAutoEmails();
                    } else
                    {
                        autoEmail.IsChecked = false;
                    }
                }
            }
            sqlite_conn.Open();
            sqlite_cmd = sqlite_conn.CreateCommand();
            RefreshHolidays();
            RefreshPeople();
            sqlite_conn.Close();
        }

        private void SetupDBs()
        {
            sqlite_conn = new SQLiteConnection("Data Source=peopleAndHolidays.db;Version=3;New=True;Compress=True;");
            sqlite_conn.Open();
            sqlite_cmd = sqlite_conn.CreateCommand();
            sqlite_cmd.CommandText = "CREATE TABLE if not exists People (LastName varchar(64), FirstName varchar(64), EmailAddress varchar(255), EmailLastYear varchar(3), BirthDate TEXT);";
            sqlite_cmd.ExecuteNonQuery();
            sqlite_cmd.CommandText = "CREATE TABLE if not exists Holidays (Date TEXT, Subject varchar(255), Message TEXT);";
            sqlite_cmd.ExecuteNonQuery();
            sqlite_conn.Close();
        }

        private void SendAutoEmails()
        {
            MailMessage message;
            string emailAddress;
            string now = DateTime.Now.Date.ToString("yyyy/MM/dd");
            sqlite_conn.Open();
            sqlite_cmd = sqlite_conn.CreateCommand();
            sqlite_cmd.CommandText = "SELECT * FROM Holidays WHERE Date='" + now + "';";
            using (sqlite_reader = sqlite_cmd.ExecuteReader())
            {
                while (sqlite_reader.Read())
                {
                    string subject = sqlite_reader.GetString(1);
                    string body = sqlite_reader.GetString(2);
                    if (subject == "Its your birthday!")
                    {
                        SendBirthDayEmail(subject, body);
                    }
                    else
                    {
                        sqlite_cmd = sqlite_conn.CreateCommand();
                        sqlite_cmd.CommandText = "SELECT EmailAddress FROM People;";
                        using (tempReader = sqlite_cmd.ExecuteReader())
                        {
                            while (tempReader.Read())
                            {
                                emailAddress = tempReader.GetString(0);
                                message = new MailMessage(myEmail, emailAddress, subject, body);
                                client.Send(message);
                            }
                        }
                    }
                    autoEmailsSent.Visibility = Visibility.Visible;
                }
            }
            sqlite_conn.Close();
        }

        private void SendBirthDayEmail(string subject, string body)
        {
            string emailAddress;
            MailMessage message;
            string fullName = body.Substring(16);
            fullName = fullName.Remove(fullName.Length - 3);
            string[] names = fullName.Split(' ');
            string fName = names[0];
            string lName = names[1];
            sqlite_cmd = sqlite_conn.CreateCommand();
            sqlite_cmd.CommandText = "SELECT EmailAddress FROM People WHERE FirstName='" + fName + "' AND LastName='" + lName + "';";
            using (tempReader = sqlite_cmd.ExecuteReader())
            {
                while (tempReader.Read())
                {
                    emailAddress = tempReader.GetString(0);
                    message = new MailMessage(myEmail, emailAddress, subject, body);
                    client.Send(message);
                }
            }
        }

        private void AttachBtn_Click(object sender, RoutedEventArgs e)
        {
            tabs.SelectedValue = sendTab;
            OpenFileDialog fileDialog = new OpenFileDialog
            {
                FileName = "",
                DefaultExt = ".txt",
                Filter = "All|*.*"
            };
            Nullable<bool> result = fileDialog.ShowDialog();
            string filename = "";
            if (result == true)
            {
                filename = fileDialog.FileName;
                attachmentObj = new Attachment(filename);
                attachment = true;
                docAttached.Visibility = Visibility.Visible;
            } else
            {
                attachmentObj = null;
                attachment = false;
                docAttached.Visibility = Visibility.Hidden;
            }
        }

        private void ChooseBtn_Click(object sender, RoutedEventArgs e)
        {
            tabs.SelectedValue = sendTab;
            chooseWindow.RefreshChooseWindow();
            chooseWindow.ShowDialog();
            recipients = chooseWindow.chosenRecipients;
            if (recipients == null)
            {
                recipsChosen.Visibility = Visibility.Hidden;
            } else
            {
                if (recipients.Count < 1)
                {
                    recipsChosen.Visibility = Visibility.Hidden;
                } else
                {
                    recipsChosen.Visibility = Visibility.Visible;
                }
            }
        }

        private void SendBtn_Click(object sender, RoutedEventArgs e)
        {
            tabs.SelectedValue = sendTab;
            MessageBoxImage icon = MessageBoxImage.Error;
            if (emailSubject.Text == "")
            {
                MessageBox.Show("Please enter an e-mail subject", "Error", MessageBoxButton.OK, icon);
                return;
            }
            if (emailBody.Text == "")
            {
                MessageBox.Show("Please enter an e-mail body", "Error", MessageBoxButton.OK, icon);
                return;
            }
            if (recipients == null)
            {
                MessageBox.Show("Please choose at least one recipient", "Error", MessageBoxButton.OK, icon);
                return;
            }
            if (!recipients.Any() || recipients.Count < 1)
            {
                MessageBox.Show("Please choose at least one recipient", "Error", MessageBoxButton.OK, icon);
                return;
            }
            foreach (string rec in recipients)
            {
                MailMessage message = new MailMessage(myEmail, rec, emailSubject.Text, emailBody.Text);
                if (attachment)
                {
                    message.Attachments.Add(attachmentObj);
                }
                client.Send(message);
            }
            emailSent.Visibility = Visibility.Visible;
            emailSubject.Text = "";
            emailBody.Text = "";
            attachment = false;
            attachmentObj = null;
            recipients.Clear();
        }

        private void FilterLetter_Checked(object sender, RoutedEventArgs e)
        {
            filterLetterTxt.IsEnabled = true;
            submitLetterBtn.IsEnabled = true;
            filterLetterTxt.Text = "";
            filterLetterTxt.MaxLength = 1;
        }

        private void FilterLetter_Unchecked(object sender, RoutedEventArgs e)
        {
            filterLetterTxt.IsEnabled = false;
            submitLetterBtn.IsEnabled = false;
            filterLetterTxt.Text = "Enter Letter";
        }

        private void SubmitLetter_Click(object sender, RoutedEventArgs e)
        {
            string letter = filterLetterTxt.Text;
            sqlite_conn.Open();
            sqlite_cmd = sqlite_conn.CreateCommand();
            sqlite_cmd.CommandText = "SELECT * FROM People WHERE LastName LIKE '" + letter + "%';";
            sqlite_reader = sqlite_cmd.ExecuteReader();
            peopleDT = new DataTable();
            peopleDT.Load(sqlite_reader);
            peopleGrid.ItemsSource = peopleDT.DefaultView;
            sqlite_conn.Close();
        }

        private void RefreshPeopleBtn_Click(object sender, RoutedEventArgs e)
        {
            tabs.SelectedValue = peopleTab;
            if (filterLetter.IsChecked == true)
            {
                filterLetter.IsChecked = false;
                filterLetterTxt.IsEnabled = false;
                submitLetterBtn.IsEnabled = false;
            }
            sqlite_conn.Open();
            sqlite_cmd = sqlite_conn.CreateCommand();
            RefreshPeople();
            sqlite_conn.Close();
        }

        private void RefreshPeople()
        {
            sqlite_cmd.CommandText = "SELECT * FROM People ORDER BY LastName ASC;";
            sqlite_reader = sqlite_cmd.ExecuteReader();
            peopleDT = new DataTable();
            peopleDT.Load(sqlite_reader);
            peopleGrid.ItemsSource = peopleDT.DefaultView;
        }

        private void RefreshHolidaysBtn_Click(object sender, RoutedEventArgs e)
        {
            tabs.SelectedValue = holidaysTab;
            sqlite_conn.Open();
            sqlite_cmd = sqlite_conn.CreateCommand();
            RefreshHolidays();
            sqlite_conn.Close();
        }

        private void RefreshHolidays()
        {
            sqlite_cmd.CommandText = "SELECT * FROM Holidays ORDER BY Date ASC;";
            sqlite_reader = sqlite_cmd.ExecuteReader();
            holidaysDT = new DataTable();
            holidaysDT.Load(sqlite_reader);
            holidayGrid.ItemsSource = holidaysDT.DefaultView;
        }

        private void AddPersonBtn_Click(object sender, RoutedEventArgs e)
        {
            tabs.SelectedValue = peopleTab;
            addPersonWindow.person = null;
            addPersonWindow.ShowDialog();
            try
            {
                string fName = addPersonWindow.person.firstName;
                string lName = addPersonWindow.person.lastName;
                string emailAddress = addPersonWindow.person.emailAddress;
                string emailLastYear = (addPersonWindow.person.emailPrevYear) ? "YES" : "NO";
                string birthDay = addPersonWindow.person.birthDay.Date.ToString("yyyy/MM/dd");
                string birthDayThisNextYear = BirthDayPassed();
                sqlite_cmd = sqlite_conn.CreateCommand();
                sqlite_cmd.CommandText = "INSERT INTO People (FirstName, LastName, EmailAddress, EmailLastYear, BirthDate) VALUES ('" + fName + "', '"
                    + lName + "', '" + emailAddress + "', '" + emailLastYear + "', '" + birthDay + "');";
                sqlite_conn.Open();
                sqlite_cmd.ExecuteNonQuery();
                sqlite_cmd.CommandText = "INSERT INTO Holidays (Date, Subject, Message) VALUES ('" + birthDayThisNextYear
                    + "', 'Its your birthday!', 'Happy Birthday, " + fName + " " + lName + "!!!');";
                sqlite_cmd.ExecuteNonQuery();
                RefreshPeople();
                RefreshHolidays();
                sqlite_conn.Close();
            } catch (Exception exception)
            {
                return;
            }
            
        }

        private string BirthDayPassed()
        {
            DateTime birthDay;
            int year = DateTime.Now.Date.Year;
            int month = addPersonWindow.person.birthDay.Date.Month;
            int day = addPersonWindow.person.birthDay.Date.Day;
            if (DateTime.Now.Date.Month > month)
            {
                year = DateTime.Now.Date.Year + 1;
                birthDay = new DateTime(year, month, day);
            } else if (DateTime.Now.Date.Month < month)
            {
                birthDay = new DateTime(year, month, day);
            } else
            {
                if (DateTime.Now.Date.Day <= day)
                {
                    birthDay = new DateTime(year, month, day);
                } else
                {
                    year = DateTime.Now.Date.Year + 1;
                    birthDay = new DateTime(year, month, day);
                }
            }
            return birthDay.Date.ToString("yyyy/MM/dd");
        }

        private void AddHolidayBtn_Click(object sender, RoutedEventArgs e)
        {
            tabs.SelectedValue = holidaysTab;
            addHolidayWindow.holiday = null;
            addHolidayWindow.ShowDialog();
            try
            {
                string date = addHolidayWindow.holiday.date.Date.ToString("yyyy/MM/dd");
                string subject = addHolidayWindow.holiday.subject;
                string message = addHolidayWindow.holiday.message;
                sqlite_cmd = sqlite_conn.CreateCommand();
                sqlite_cmd.CommandText = "INSERT INTO Holidays (Date, Subject, Message) VALUES ('" + date + "', '" + subject + "', '" + message + "');";
                sqlite_conn.Open();
                sqlite_cmd.ExecuteNonQuery();
                RefreshHolidays();
                sqlite_conn.Close();
            } catch (Exception exception)
            {
                return;
            }
            
        }

        private void ClearHolidaysBtn_Click(object sender, RoutedEventArgs e)
        {
            tabs.SelectedValue = holidaysTab;
            MessageBoxImage icon = MessageBoxImage.Warning;
            MessageBoxResult messageBoxResult = MessageBox.Show("Are you sure? This will delete the entire contents of the Holidays database.", "Confirmation", MessageBoxButton.YesNo, icon);
            if (messageBoxResult == MessageBoxResult.Yes)
            {
                sqlite_conn.Open();
                sqlite_cmd = sqlite_conn.CreateCommand();
                sqlite_cmd.CommandText = "DELETE FROM Holidays;";
                sqlite_cmd.ExecuteNonQuery();
                sqlite_conn.Close();
                holidaysDT = new DataTable();
                holidayGrid.ItemsSource = holidaysDT.DefaultView;
            }
        }

        private void ClearPeopleBtn_Click(object sender, RoutedEventArgs e)
        {
            tabs.SelectedValue = peopleTab;
            MessageBoxImage icon = MessageBoxImage.Warning;
            MessageBoxResult messageBoxResult = MessageBox.Show("Are you sure? This will delete the entire contents of the People database.", "Confirmation", MessageBoxButton.YesNo, icon);
            if (messageBoxResult == MessageBoxResult.Yes)
            {
                sqlite_conn.Open();
                sqlite_cmd = sqlite_conn.CreateCommand();
                sqlite_cmd.CommandText = "DELETE FROM People;";
                sqlite_cmd.ExecuteNonQuery();
                sqlite_conn.Close();
                peopleDT = new DataTable();
                peopleGrid.ItemsSource = peopleDT.DefaultView;
            }
        }

        private void Close_Click(object sender, System.ComponentModel.CancelEventArgs e)
        {
            WriteAutoEmails();
            sqlite_reader.Dispose();
            Environment.Exit(0);
        }

        private void Close_Click(object sender, RoutedEventArgs e)
        {
            WriteAutoEmails();
            sqlite_reader.Dispose();
            Environment.Exit(0);
        }

        private void WriteAutoEmails()
        {
            string yn = (autoEmail.IsChecked == true) ? "YES" : "NO";
            File.WriteAllText("autoEmails.txt", yn);
        }

        private void AboutMenu_Click(object sender, RoutedEventArgs e)
        {
            string s = "To set up automatic e-mailing on holidays, right-click 'HolidayMailer.exe' and choose 'Create shortcut'\n" 
                + "Then, move the shortcut you just created into this location and restart the app:\n\nC:\\Users\\<user name>\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup";
            MessageBox.Show("HolidayMailer made by George Polyak\nUsing WPF .NET Framework version 4.6.1\n\n\n" + s);
        }

    }
}
