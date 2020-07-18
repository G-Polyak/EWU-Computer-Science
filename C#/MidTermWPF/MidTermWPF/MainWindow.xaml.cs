/* 
     Name: George Polyak
     Class Description: Provides GUI functionality to watch a given directory and report
     any changes to the user as well as write them to a database, allowing the user to make queries 
     on a seperate tab window.
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
using System.IO;
using System.Data.SQLite;
using System.Data;

namespace MidTermWPF
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {

        private SQLiteConnection sqlite_conn;
        private SQLiteCommand sqlite_cmd;
        private SQLiteDataReader sqlite_reader;
        private DataTable dt;
        private string path;
        private string watchExt;
        private FileSystemWatcher watcher;
        private string line;
        private List<WatchEntry> currOut;
        private WatchEntry currEntry;

        public MainWindow()
        {
            InitializeComponent();
            currOut = new List<WatchEntry>();
            sqlite_conn = new SQLiteConnection("Data Source=filewatcher.db;Version=3;New=True;Compress=True;");
            sqlite_conn.Open();
            sqlite_cmd = sqlite_conn.CreateCommand();
            sqlite_cmd.CommandText = "CREATE TABLE if not exists FileInfo (Name varchar(255), Path varchar(255), Event varchar(255), Time varchar(64), Extension varchar(8));";
            sqlite_cmd.ExecuteNonQuery();
            sqlite_conn.Close();
            dt = new DataTable();
        }

        private void SetupWatcher()
        {
            watcher = new FileSystemWatcher(path);
            watcher.NotifyFilter = NotifyFilters.LastAccess
                                 | NotifyFilters.LastWrite
                                 | NotifyFilters.FileName
                                 | NotifyFilters.DirectoryName;

            if (watchExt != "All")
            {
                watcher.Filter = "*." + watchExt;
            }
            watcher.IncludeSubdirectories = true;
            watcher.Changed += OnChanged;
            watcher.Created += OnChanged;
            watcher.Deleted += OnChanged;
            watcher.Renamed += OnRenamed;
        }

        private void OnRenamed(object sender, RenamedEventArgs e)
        {
            string time = DateTime.Now.ToString();
            string oldName = e.OldName;
            string name = e.Name;
            if (oldName.Contains(@"\"))
            {
                int index = 0;
                for (int i = 0; i < oldName.Length; i++)
                {
                    if (oldName[i].ToString() == @"\")
                    {
                        index = i;
                    }
                }
                oldName = oldName.Substring(index + 1);
                name = name.Substring(index + 1);
            }
            string fullPath = e.FullPath;
            string evnt = $"{oldName} RENAMED TO {name}";
            line = $"FILE: {fullPath} {evnt} AT {time}";
            currEntry = new WatchEntry(name, fullPath, evnt, time, watchExt);
            AddAcrossThread();
        }

        private void OnChanged(object sender, FileSystemEventArgs e)
        {
            string time = DateTime.Now.ToString();
            string name = e.Name;
            if (name.Contains(@"\"))
            {
                int index = 0;
                for (int i = 0; i < name.Length; i++)
                {
                    if (name[i].ToString() == @"\")
                    {
                        index = i;
                    }
                }
                name = name.Substring(index + 1);
            }
            string fullPath = e.FullPath;
            string evnt = e.ChangeType.ToString().ToUpper();
            line = $"FILE: {fullPath} {evnt} AT {time}";
            currEntry = new WatchEntry(name, fullPath, evnt, time, watchExt);
            AddAcrossThread();
        }

        private void AddAcrossThread()
        {
            if (this.dirPath.Dispatcher.CheckAccess())
            {
                watcherOut.Text += line + "\n";
                currOut.Add(currEntry);
                return;
            }
            this.dirPath.Dispatcher.Invoke(
                System.Windows.Threading.DispatcherPriority.Normal,
                new Action(AddAcrossThread));
        }

        private void enableStarts()
        {
            this.startMain.IsEnabled = true;
            this.startMenu.IsEnabled = true;
            this.startTool.IsEnabled = true;
            this.stopMain.IsEnabled = false;
            this.stopMenu.IsEnabled = false;
            this.stopTool.IsEnabled = false;
        }

        private void enableStops()
        {
            this.startMain.IsEnabled = false;
            this.startMenu.IsEnabled = false;
            this.startTool.IsEnabled = false;
            this.stopMain.IsEnabled = true;
            this.stopMenu.IsEnabled = true;
            this.stopTool.IsEnabled = true;
        }

        private void Start_Click(object sender, RoutedEventArgs e)
        {
            watcher.EnableRaisingEvents = true;
            enableStops();
            this.submitA.IsEnabled = false;
        }

        private void Stop_Click(object sender, RoutedEventArgs e)
        {
            watcher.EnableRaisingEvents = false;
            enableStarts();
            this.submitA.IsEnabled = true;
        }

        private void SubmitA_Click(object sender, RoutedEventArgs e)
        {
            path = this.dirPath.Text;
            if (!Directory.Exists(path))
            {
                MessageBox.Show("Entered path does not exist.");
            } else
            {
                watchExt = this.extPickA.Text;
                enableStarts();
                SetupWatcher();
            }
        }

        private void Query_Click(object sender, RoutedEventArgs e)
        {
            if (this.tabs.SelectedValue == this.tab1)
            {
                ToggleTabD();
            } else
            {
                ToggleTabW();
            }
        }

        private void Write_Click(object sender, RoutedEventArgs e)
        {
            if (currOut.Count == 0)
            {
                MessageBox.Show("No data available to write");
            } else
            {
                string command = "INSERT INTO FileInfo (Name, Path, Event, Time, Extension) VALUES ";
                foreach (WatchEntry s in currOut)
                {
                    command += s.ToString();
                }
                command = command.Remove(command.Length - 2); //removes last comma
                sqlite_cmd.CommandText = command+ ";";
                sqlite_conn.Open();
                sqlite_cmd.ExecuteNonQuery();
                sqlite_conn.Close();
                currOut.Clear();
                this.watcherOut.Clear();
            }
        }

        private void ClearDB_Click(object sender, RoutedEventArgs e)
        {
            MessageBoxResult messageBoxResult = MessageBox.Show("Are you sure? This will delete the entire contents of the database.", "Confirmation", MessageBoxButton.YesNo);
            if (messageBoxResult == MessageBoxResult.Yes)
            {
                sqlite_conn.Open();
                sqlite_cmd = sqlite_conn.CreateCommand();
                sqlite_cmd.CommandText = "DELETE FROM FileInfo;";
                sqlite_cmd.ExecuteNonQuery();
                sqlite_conn.Close();
                dt = new DataTable();
                this.dataGrid.ItemsSource = dt.DefaultView;
            }
        }

        private void SubmitB_Click(object sender, RoutedEventArgs e)
        {
            sqlite_conn.Open();
            sqlite_cmd = sqlite_conn.CreateCommand();
            if (this.extPickB.Text == "All")
            {
                sqlite_cmd.CommandText = "SELECT * FROM FileInfo;";
            } else
            {
                sqlite_cmd.CommandText = "SELECT * FROM FileInfo WHERE Extension='" + this.extPickB.Text + "';";
            }
            sqlite_reader = sqlite_cmd.ExecuteReader();
            dt = new DataTable();
            dt.Load(sqlite_reader);
            this.dataGrid.ItemsSource = dt.DefaultView;
            sqlite_conn.Close();
        }

        private void AboutMenu_Click(object sender, RoutedEventArgs e)
        {
            MessageBox.Show("FileSystemWatcherGUI made by George Polyak\nUsing WPF .NET Framework version 4.6.1");
        }

        private void End()
        {
            if (watcherOut.Text != "")
            {
                MessageBox.Show("Please write the remaining data to the database before closing.");
            } else
            {
                Environment.Exit(0);
            }
        }

        private void Close_Click(object sender, System.ComponentModel.CancelEventArgs e)
        {
            e.Cancel = true;
            End();
        }

        private void Close_Click(object sender, RoutedEventArgs e)
        {
            End();
        }

        private void Close_Click(object sender, EventArgs e)
        {
            End();
        }

        private void OnTabFocusW(object sender, RoutedEventArgs e)
        {
            ToggleTabW();
        }

        private void OnTabFocusD(object sender, RoutedEventArgs e)
        {
            ToggleTabD();
        }

        private void ToggleTabW()
        {
            this.tabs.SelectedValue = this.tab1;
            this.queryMenu.Header = "_Query DB";
            this.queryTool.Content = "Q";
            string tip = "Opens tab for database query options";
            this.queryMenu.ToolTip = tip;
            this.queryTool.ToolTip = tip;
        }

        private void ToggleTabD()
        {
            this.tabs.SelectedValue = this.tab2;
            this.queryMenu.Header = "W_atch Files";
            this.queryTool.Content = "FW";
            string tip = "Opens tab for file watching";
            this.queryMenu.ToolTip = tip;
            this.queryTool.ToolTip = tip;
        }

    }
}
