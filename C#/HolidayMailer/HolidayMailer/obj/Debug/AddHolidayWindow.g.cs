﻿#pragma checksum "..\..\AddHolidayWindow.xaml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "A12C8B78F02F34C72A2B97FB9CF55B8D7165C6B8"
//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.42000
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

using HolidayMailer;
using System;
using System.Diagnostics;
using System.Windows;
using System.Windows.Automation;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Markup;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Effects;
using System.Windows.Media.Imaging;
using System.Windows.Media.Media3D;
using System.Windows.Media.TextFormatting;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Shell;


namespace HolidayMailer {
    
    
    /// <summary>
    /// AddHolidayWindow
    /// </summary>
    public partial class AddHolidayWindow : System.Windows.Window, System.Windows.Markup.IComponentConnector {
        
        
        #line 1 "..\..\AddHolidayWindow.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal HolidayMailer.AddHolidayWindow addHolidayWindow;
        
        #line default
        #line hidden
        
        
        #line 10 "..\..\AddHolidayWindow.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.Button submitHoliday;
        
        #line default
        #line hidden
        
        
        #line 12 "..\..\AddHolidayWindow.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox holidayYearTxt;
        
        #line default
        #line hidden
        
        
        #line 13 "..\..\AddHolidayWindow.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox holidayMonthTxt;
        
        #line default
        #line hidden
        
        
        #line 14 "..\..\AddHolidayWindow.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox holidayWeekdayTxt;
        
        #line default
        #line hidden
        
        
        #line 16 "..\..\AddHolidayWindow.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox holidaySubjectTxt;
        
        #line default
        #line hidden
        
        
        #line 18 "..\..\AddHolidayWindow.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox holidayMessageTxt;
        
        #line default
        #line hidden
        
        private bool _contentLoaded;
        
        /// <summary>
        /// InitializeComponent
        /// </summary>
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "4.0.0.0")]
        public void InitializeComponent() {
            if (_contentLoaded) {
                return;
            }
            _contentLoaded = true;
            System.Uri resourceLocater = new System.Uri("/HolidayMailer;component/addholidaywindow.xaml", System.UriKind.Relative);
            
            #line 1 "..\..\AddHolidayWindow.xaml"
            System.Windows.Application.LoadComponent(this, resourceLocater);
            
            #line default
            #line hidden
        }
        
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "4.0.0.0")]
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Never)]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Design", "CA1033:InterfaceMethodsShouldBeCallableByChildTypes")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Maintainability", "CA1502:AvoidExcessiveComplexity")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1800:DoNotCastUnnecessarily")]
        void System.Windows.Markup.IComponentConnector.Connect(int connectionId, object target) {
            switch (connectionId)
            {
            case 1:
            this.addHolidayWindow = ((HolidayMailer.AddHolidayWindow)(target));
            
            #line 8 "..\..\AddHolidayWindow.xaml"
            this.addHolidayWindow.Closing += new System.ComponentModel.CancelEventHandler(this.AddHolidayWindow_Close);
            
            #line default
            #line hidden
            return;
            case 2:
            this.submitHoliday = ((System.Windows.Controls.Button)(target));
            
            #line 10 "..\..\AddHolidayWindow.xaml"
            this.submitHoliday.Click += new System.Windows.RoutedEventHandler(this.SubmitHoliday_Click);
            
            #line default
            #line hidden
            return;
            case 3:
            this.holidayYearTxt = ((System.Windows.Controls.TextBox)(target));
            
            #line 12 "..\..\AddHolidayWindow.xaml"
            this.holidayYearTxt.GotFocus += new System.Windows.RoutedEventHandler(this.Date_GotFocus);
            
            #line default
            #line hidden
            
            #line 12 "..\..\AddHolidayWindow.xaml"
            this.holidayYearTxt.LostFocus += new System.Windows.RoutedEventHandler(this.HolidayYearTxt_LostFocus);
            
            #line default
            #line hidden
            return;
            case 4:
            this.holidayMonthTxt = ((System.Windows.Controls.TextBox)(target));
            
            #line 13 "..\..\AddHolidayWindow.xaml"
            this.holidayMonthTxt.GotFocus += new System.Windows.RoutedEventHandler(this.Date_GotFocus);
            
            #line default
            #line hidden
            
            #line 13 "..\..\AddHolidayWindow.xaml"
            this.holidayMonthTxt.LostFocus += new System.Windows.RoutedEventHandler(this.HolidayMonthTxt_LostFocus);
            
            #line default
            #line hidden
            return;
            case 5:
            this.holidayWeekdayTxt = ((System.Windows.Controls.TextBox)(target));
            
            #line 14 "..\..\AddHolidayWindow.xaml"
            this.holidayWeekdayTxt.GotFocus += new System.Windows.RoutedEventHandler(this.Date_GotFocus);
            
            #line default
            #line hidden
            
            #line 14 "..\..\AddHolidayWindow.xaml"
            this.holidayWeekdayTxt.LostFocus += new System.Windows.RoutedEventHandler(this.HolidayWeekdayTxt_LostFocus);
            
            #line default
            #line hidden
            return;
            case 6:
            this.holidaySubjectTxt = ((System.Windows.Controls.TextBox)(target));
            return;
            case 7:
            this.holidayMessageTxt = ((System.Windows.Controls.TextBox)(target));
            return;
            }
            this._contentLoaded = true;
        }
    }
}

