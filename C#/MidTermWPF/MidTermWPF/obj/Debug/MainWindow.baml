   M S B A M L   `   `   `  ����E  AMidTermWPF, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null'clr-namespace:MidTermWPF
MidTermWPF  5   	       MidTermWPF.MainWindow   5   	   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   *localclr-namespace:MidTermWPF     5   	   -   5          �Name$  
mainWindow��5   	    � Title$ FileWatcherGUI��5   	   $	��450��6    $	��800��6-    � 
ResizeMode$ NoResize=�69    � Icon$ Icon.ico��6O   .��5	      � .+�5
   
   �� -   5         ��Name$
 tabs��5
      $	��391��6#   $��0,28,0,0q�60   $	��792��6B   $
��Left=�6N   $��Center=�6i   $��Center=�6�   $	��Top=�6�   .@�5      �� -   5         ��Name$
 tab1��5      $��Watcher��6$   $��108,0,-112,-2q�65   .��5      � ���	#FFE5E5E5 � ColumnDefinitions 5      �� 5      $
��197* �6+   �� 5      $
��589* �6+   .+�5      �� -   5         ��Name$ 
watcherOut��5   �   $��Disabled=�6   $
��Auto=�6T   $
��Left=�6�   $	��343��6�   $��	10,10,0,0q�6�    �� TextWrapping$
 Wrap=�6�   $�� ��6�   $	��Top=�6�   $	��427��6  $��2��6  	 �� 
IsReadOnly	 . 6'  �� 5      -   5        $ dirPath��5      $
��Left=�6/   $��23��6J   $��
245,41,0,0q�6V   $
 Wrap=�6j   $	��C:\��6~   $	��Top=�6�   $	��240��6�   $��1��6�   *
 �� #IsInactiveSelectionHighlightEnabled
 . 4  .System.Windows.Automation.AutomationProperties   IsRequiredForForm . 6�   �� 5      $��Enter directory path:��6   $
��Left=�6<   $��
245,10,0,0q�6W   $	��Top=�6k   $��1��6�   �� 5      -   5         ��Name$ extPickA��5      $
��Left=�61   $��
490,42,0,0q�6L   $	��Top=�6`   $��70��6x   $��1��6�    � ToolTip$* $Shows available file extension types��6�    �� 
IsEditable . .@�5      ��  �� 
IsSelected . .��69   All6>   �� 5      .��6'   txt6,   �� 5      .��6'   exe6,   �� 5      .��6'   cs6+   �� 5      .��6'   jpg6,   �� 5      .��6'   Custom6/   5      �� 5      $��
Extension:��6   $
��Left=�61   $��
490,10,0,0q�6L   $	��Top=�6`   $��1��6x   �� 5      -   5         ��Name$ submitA��5      $��Submit��6.   $
��Left=�6?   $��
245,69,0,0q�6Z   $	��Top=�6n   $	��315��6�   $��1��6�   $$ Submits entered directory path��6�   �� 5      -   5        $ 	startMain��5      $��Start��60   $
��Left=�6@   $��245,278,0,0q�6[   $	��Top=�6p   $	��147��6�   $��48��6�   $��1��6�   {�.  $' !Starts watching a given directory��6�   �� 5      -	   5        $ stopMain��5      $
��Stop��6/   $
��Left=�6>   $��413,278,0,0q�6Y   $	��Top=�6n   $	��147��6�   $��48��6�   {�.  $��1��6�   $$ Stops watching given directory��6�   5       5!      �� 5"      -
   5        $
 tab2��5"      $��Database��6$   $��116,0,-120,-2q�66   .��5#      � ���	#FFE5E5E5 5$      �� 5%      $
��157* �6+   �� 5&      $	��24* �6+   �� 5'      $
��605* �6+   .+�5)      &   System.Windows.Controls.DataGrid  -   5          Name$ dataGrid��5)      $
��Left=�61   $	��343��6L   $��	10,10,0,0q�6Y   $	��Top=�6l   $	��605��6�   $��3��6�      
IsReadOnly . 6�   �� 5*      $��
Extension:��6   $
��Left=�61   $��
477,10,0,0q�6L   $	��Top=�6`   $��2��6x   �� 5+      -   5        $ extPickB��5+      $
��Left=�61   $��
477,41,0,0q�6L   $	��Top=�6`   $��70��6x   $��2��6�   $* $Shows available file extension types��6�    . .@�5,      ��  . $	��All��69   �� 5-      .��6'   txt6,   �� 5.      .��6'   exe6,   �� 5/      .��6'   cs6+   �� 50      .��6'   jpg6,   �� 51      .��6'   Custom6/   52      �� 53      -   5        $ submitB��53      $��Submit��6.   $��2��6?   $
��Left=�6O   $��
477,75,0,0q�6j   $	��Top=�6~   $��75��6�   $ Submits query��6�   �� 54      -   5        $ clearDB��54      $��Clear DB��6.   $��2��6A   $
��Left=�6Q   $��477,319,0,0q�6l   $	��Top=�6�   $��75��6�   $" ***Clears ENTIRE database***��6�   55      56      57      o� 58   
   $
��Left=�6   $��22��6*   $	��Top=�66   $	��792��6N   $��0,1,0,0q�6Z   .@�59      m� $��_File��6   .@�5:      m� -   5         m�Name$ 	startMenu��5:      $��_Start��6.   {�.  $' !Starts watching a given directory��6d   m� 5;      -   5        $ stopMenu��5;      $��_Stop��6-   {�.  $$ Stops watching given directory��6a   �� 5<      m� 5=      -   5        $ 	queryMenu��5=      $��	_Query DB��6.   $* $Opens tab for database query options��6U   m� 5>      -   5        $ 	writeMenu��5>      $��_Write to DB��6.   $A ;Writes contents of current watching session to the database��6X   �� 5?      m� 5@      -   5        $ exitMenu��5@      $��E_xit��6-   $ Closes the program��6P   5A      m� 5B      $��_Help��6   .@�5C      m� -   5        $ 	aboutMenu��5C      $��_About��6.   $% Shows details about the program��6V   5D      5E      `� 5F   
   $
��Left=�6   $	��116��61   $��755,164,0,0q�6>   $	��Top=�6S   $��37��6k    `� Orientation$ Vertical=�6v   .�5G      c� $
��Left=�6   $	��114��61   $	��Top=�6>   $��37��6V   $��-4,0,-20,-90q�6a   .@�5H      �� -   5        $ 	startTool��5H      $��Start��6,   $��50��6<   {�.  $' !Starts watching a given directory��6m   �� 5I      -   5        $ stopTool��5I      $
��Stop��6+   $��50��6:   {�.  $$ Stops watching given directory��6j   �� 5J      -   5        $ 	queryTool��5J      $��Q��6,   $��50��68   $* $Opens tab for database query options��6W   �� 5K      -   5        $ 	writeTool��5K      $��W��6,   $��50��68   $A ;Writes contents of current watching session to the database��6W   5L      5M      5O      5P      