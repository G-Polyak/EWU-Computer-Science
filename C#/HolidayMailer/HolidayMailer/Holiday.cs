/* 
     Name: George Polyak
     Class Description: Object class for holding all of the necessary info of a Holiday
*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HolidayMailer
{
    public class Holiday
    {

        public DateTime date;
        public string subject;
        public string message;

        public Holiday(DateTime holidayDate, string sub, string msg)
        {
            date = holidayDate;
            subject = sub;
            message = msg;
        }

    }
}
