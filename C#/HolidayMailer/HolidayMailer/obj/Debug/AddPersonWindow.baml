   M S B A M L   `   `   `  ����H  DHolidayMailer, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null-clr-namespace:HolidayMailerHolidayMailer  5   	   #    HolidayMailer.AddPersonWindow   5   	   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   -localclr-namespace:HolidayMailer     5   	   -   5          �Name$  addPersonWindow��5   	    � Title$ 
Add Person��5   	   $	��176��6   $	��356��6)    � 
ResizeMode$ NoResize=�65    � WindowStartupLocation$ CenterScreen=�6k   .��5	      � .+�5
   
   �� -   5         ��Name$ submitPerson��5
      $��Submit��6'   $
��Left=�68   $��254,107,0,0q�6S   $	��Top=�6h   $��75��6�   6�   �� 5   
   $��First Name:��6   $
��Left=�6&   $��4,0,0,0q�6A   $	��Top=�6R   �� 5   
   -   5         ��Name$ firstNameTxt��5      $
��Left=�6(   $��23��6C   $��4,24,0,0q�6O    �� TextWrapping$
 Wrap=�6a   $�� ��6u   $	��Top=�6}   $	��120��6�   �� 5   
   $��
Last Name:��6   $
��Left=�6%   $��4,42,0,0q�6@   $	��Top=�6R   �� 5   
   -   5        $ lastNameTxt��5      $
��Left=�6'   $��23��6B   $��4,64,0,0q�6N   $
 Wrap=�6`   $�� ��6t   $	��Top=�6|   $	��120��6�   �� 5   
   $��E-Mail Address:��6   $
��Left=�6*   $��4,82,0,0q�6E   $	��Top=�6W   �� 5   
   -   5        $ emailAddressTxt��5      $
��Left=�6+   $��23��6F   $��	4,104,0,0q�6R   $
 Wrap=�6e   $�� ��6y   $	��Top=�6�   $	��180��6�   �� 5   
   $��Date of Birth:��6   $
��Left=�6)   $��	184,0,0,0q�6D   $	��Top=�6W   �� 5   
   -   5        $ birthYearTxt��5      $
��Left=�6(   $��23��6C   $��
184,24,0,0q�6O   $
 Wrap=�6c   $
��YYYY��6w   $	��Top=�6�   $��36��6�    �� 	MaxLength$ 4��6�   6�   �� 5   
   -   5        $ birthMonthTxt��5      $
��Left=�6)   $��23��6D   $��
222,24,0,0q�6P   $
 Wrap=�6d   $��MM��6x   $	��Top=�6�   $��28��6�   $ 2��6�   6�   �� 5   
   -   5        $ birthWeekdayTxt��5      $
��Left=�6+   $��23��6F   $��
252,24,0,0q�6R   $
 Wrap=�6f   $��DD��6z   $	��Top=�6�   $��28��6�   $ 2��6�   6�   �� 5   
   -	   5         ��Name$ emailPrevYear��5      $0��*Did they send a holiday 
E-Mail last year?��6*   $
��Left=�6d   $��
184,64,0,0q�6   $	��Top=�6�   $	��145��6�   5      5      