/* 
     Name: George Polyak
     Class Description: Provides GUI functionality for gathering the info of a new holiday
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
    /// Interaction logic for AddHolidayWindow.xaml
    /// </summary>
    public partial class AddHolidayWindow : Window
    {

        public Holiday holiday;

        public AddHolidayWindow()
        {
            InitializeComponent();
        }

        private void AddHolidayWindow_Close(object sender, System.ComponentModel.CancelEventArgs e)
        {
            e.Cancel = true;
            Visibility = Visibility.Hidden;
        }

        private void SubmitHoliday_Click(object sender, RoutedEventArgs e)
        {
            MessageBoxImage icon = MessageBoxImage.Error;
            try
            {
                if (holidaySubjectTxt.Text == "" || holidayMessageTxt.Text == "")
                {
                    throw new Exception();
                }
                DateTime date = new DateTime(int.Parse(holidayYearTxt.Text), int.Parse(holidayMonthTxt.Text), int.Parse(holidayWeekdayTxt.Text));
                holiday = new Holiday(date, holidaySubjectTxt.Text, holidayMessageTxt.Text);
                ResetHolidayWindow();
                Visibility = Visibility.Hidden;
            }
            catch (Exception exeption)
            {
                MessageBox.Show("Please enter valid Holiday information", "Error", MessageBoxButton.OK, icon);
            }
        }

        private void ResetHolidayWindow()
        {
            holidaySubjectTxt.Text = "";
            holidayMessageTxt.Text = "";
            holidayYearTxt.Text = "YYYY";
            holidayMonthTxt.Text = "MM";
            holidayWeekdayTxt.Text = "DD";
        }

        private void Date_GotFocus(object sender, RoutedEventArgs e)
        {
            TextBox textBox = (TextBox)sender;
            textBox.Text = "";
        }

        private void HolidayYearTxt_LostFocus(object sender, RoutedEventArgs e)
        {
            if (holidayYearTxt.Text == "")
            {
                holidayYearTxt.Text = "YYYY";
            }
        }

        private void HolidayMonthTxt_LostFocus(object sender, RoutedEventArgs e)
        {
            if (holidayMonthTxt.Text == "")
            {
                holidayMonthTxt.Text = "MM";
            }
        }

        private void HolidayWeekdayTxt_LostFocus(object sender, RoutedEventArgs e)
        {
            if (holidayWeekdayTxt.Text == "")
            {
                holidayWeekdayTxt.Text = "DD";
            }
        }
    }
}
