/* 
     Name: George Polyak
     Class Description: Provides GUI functionality for gathering the info of a new person
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
using System.Windows.Shapes;

namespace HolidayMailer
{
    /// <summary>
    /// Interaction logic for AddPersonWindow.xaml
    /// </summary>
    public partial class AddPersonWindow : Window
    {

        public Person person;

        public AddPersonWindow()
        {
            InitializeComponent();
        }

        private void AddPersonWindow_Close(object sender, System.ComponentModel.CancelEventArgs e)
        {
            e.Cancel = true;
            Visibility = Visibility.Hidden;
        }

        private void SubmitPerson_Click(object sender, RoutedEventArgs e)
        {
            MessageBoxImage icon = MessageBoxImage.Error;
            try
            {
                if (firstNameTxt.Text == "" || lastNameTxt.Text == "")
                {
                    throw new Exception();
                }
                if (!(emailAddressTxt.Text.Contains("@") && emailAddressTxt.Text.Contains(".")))
                {
                    throw new Exception();
                }
                bool prevYear = (emailPrevYear.IsChecked == true);
                DateTime birthDate = new DateTime(int.Parse(birthYearTxt.Text), int.Parse(birthMonthTxt.Text), int.Parse(birthWeekdayTxt.Text));
                person = new Person(firstNameTxt.Text, lastNameTxt.Text, emailAddressTxt.Text, prevYear, birthDate);
                ResetPersonWindow();
                Visibility = Visibility.Hidden;
            } catch (Exception exeption)
            {
                MessageBox.Show("Please enter valid Person information", "Error", MessageBoxButton.OK, icon);
            }
        }

        private void ResetPersonWindow()
        {
            firstNameTxt.Text = "";
            lastNameTxt.Text = "";
            emailAddressTxt.Text = "";
            birthYearTxt.Text = "YYYY";
            birthMonthTxt.Text = "MM";
            birthWeekdayTxt.Text = "DD";
            emailPrevYear.IsChecked = false;
        }

        private void DoB_GotFocus(object sender, RoutedEventArgs e)
        {
            TextBox textBox = (TextBox) sender;
            textBox.Text = "";
        }

        private void BirthYearTxt_LostFocus(object sender, RoutedEventArgs e)
        {
            if (birthYearTxt.Text == "")
            {
                birthYearTxt.Text = "YYYY";
            }
        }

        private void BirthMonthTxt_LostFocus(object sender, RoutedEventArgs e)
        {
            if (birthMonthTxt.Text == "")
            {
                birthMonthTxt.Text = "MM";
            }
        }

        private void BirthWeekdayTxt_LostFocus(object sender, RoutedEventArgs e)
        {
            if (birthWeekdayTxt.Text == "")
            {
                birthWeekdayTxt.Text = "DD";
            }
        }
    }
}
