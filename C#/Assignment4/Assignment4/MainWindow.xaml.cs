/* 
     Name: George Polyak
     Class Description: Provides GUI functionality to play the ball pong game 
     and to record/display current and high scores
*/

using System;
using System.IO;
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
using System.Windows.Threading;

namespace AnimateBall
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private DispatcherTimer gameTimer = new DispatcherTimer();
        private double dx = 2;
        private double dy = 2;
        private double vertDirection = -1;
        private double horizDirection = 1;

        private double gameBallTop = 0;
        private double gameBallLeft = 0;

        private double gamePaddleTop = 0;
        private double gamePaddleLeft = 0;
        private double gamePaddleDy = 5;
        private int currentScore = 0;
        private int highScore;
        private Random rnd;
        private bool isPaused;

        public MainWindow()
        {
            InitializeComponent();
            if (!File.Exists("highscores.txt"))
            {
                highScore = 0;
            }
            else
            {
                using (StreamReader sr = File.OpenText("highscores.txt"))
                {
                    highScore = int.Parse(sr.ReadLine());
                }
            }
            gameTimer.Interval = new TimeSpan(0, 0, 0, 0, 1);
            gameTimer.Tick += GameTimer_Tick;
            rnd = new Random();
            SetupGame();
        }

        private void SetupGame()
        {
            int a = rnd.Next(2);
            vertDirection = (a == 0) ? -1 : 1;
            a = rnd.Next(2);
            horizDirection = (a == 0) ? -1 : 1;
            gameBallTop = rnd.Next((int)(myGameCanvas.Height - gameBall.Height));
            gameBallLeft = rnd.Next(31, (int)(myGameCanvas.Width - gameBall.Width));
            Canvas.SetTop(gameBall, gameBallTop);
            Canvas.SetLeft(gameBall, gameBallLeft);
            gamePaddleTop = Canvas.GetTop(gamePaddle);
            gamePaddleLeft = Canvas.GetLeft(gamePaddle);
        }

        private void GameTimer_Tick(object sender, EventArgs e)
        {
            //double xComponent = gameBall;
            //double yComponent = 0;
            if (gameBallTop <= 0  || gameBallTop >= (myGameCanvas.Height - gameBall.Height))
            {
                vertDirection *= -1;
            }
            if (gameBallLeft <= 0 || gameBallLeft >= myGameCanvas.Width - gameBall.Width)
            {
                horizDirection *= -1;
            }
            bool a = gameBallLeft == gamePaddleLeft + gamePaddle.Width;
            bool b = gameBallLeft == gamePaddleLeft + gamePaddle.Width - 1;
            bool c = gameBallLeft == gamePaddleLeft + gamePaddle.Width - 2;
            if ((a || b || c) && gameBallTop + gameBall.Height / 2 >= gamePaddleTop && 
                gameBallTop + gameBall.Height / 2 <= gamePaddleTop + gamePaddle.Height)
            {
                horizDirection = 1;
                currentScore++;
            }
            if (gameBallLeft < 3)
            {
                MessageBoxResult result = MessageBox.Show("You lost. :( Would you like to try again?", "You lose!", MessageBoxButton.YesNo);
                if (result == MessageBoxResult.Yes)
                {
                    currentScore = 0;
                    SetupGame();
                    Pause();
                    isPaused = false;
                    HSAlert.Visibility = Visibility.Hidden;
                }
                else
                {
                    if (HSAlert.IsVisible)
                    {
                        File.WriteAllText("highscores.txt", highScore.ToString());
                    }
                    Environment.Exit(0);
                }
            }

            gameBallLeft += dx * horizDirection;
            gameBallTop += dy * vertDirection;

            Canvas.SetTop(gameBall, gameBallTop);
            Canvas.SetLeft(gameBall, gameBallLeft);
            
            currScore.Content = currentScore;
            if (currentScore > highScore)
            {
                HSAlert.Visibility = Visibility.Visible;
                highScore = currentScore;
            }
        }

        private void Window_KeyDown(object sender, KeyEventArgs e)
        {
            if (!isPaused)
            {
                if (e.Key == Key.Up)
                {
                    if (gamePaddleTop - gamePaddleDy >= 0)
                        gamePaddleTop -= gamePaddleDy;
                }
                else if (e.Key == Key.Down)
                {
                    if (gamePaddleTop + gamePaddleDy + gamePaddle.Height <= myGameCanvas.Height)
                        gamePaddleTop += gamePaddleDy;
                }
                Canvas.SetTop(gamePaddle, gamePaddleTop);
            }
        }

        private void Start_Click(object sender, RoutedEventArgs e)
        {
            gameTimer.IsEnabled = true;
            this.pauseMenu.IsEnabled = true;
            this.startMenu.IsEnabled = false;
            isPaused = false;
        }

        private void Pause_Click(object sender, RoutedEventArgs e)
        {
            Pause();
        }

        private void Pause()
        {
            gameTimer.IsEnabled = false;
            this.pauseMenu.IsEnabled = false;
            this.startMenu.IsEnabled = true;
            WriteHS();
            isPaused = true;
        }

        private void WriteHS()
        {
            if (HSAlert.IsVisible)
            {
                File.WriteAllText("highscores.txt", highScore.ToString());
            }
        }

        private void High_Click(object sender, RoutedEventArgs e)
        {
            Pause();
            MessageBox.Show("Highest score recorded: " + highScore);
        }

        private void Quit_Click(object sender, RoutedEventArgs e)
        {
            WriteHS();
            Environment.Exit(0);
        }

        private void Closing_Click(object sender, System.ComponentModel.CancelEventArgs e)
        {
            WriteHS();
        }
    }
}
