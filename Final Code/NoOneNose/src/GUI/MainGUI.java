package GUI;

import javax.swing.*;
import java.awt.*;
import javax.swing.UIManager;

import Executor.Detect;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainGUI
 {
	// Main window and it's content panel. Specifically using a panel rather than the frame's content pane
	// to make it easy to work with and swap between panels easily. 
	private JFrame myFrame = new JFrame();
	private JPanel myPanel = new JPanel();
	
	public void mainFrameBuild()
	{		
		// Setting the look and feel to the current system's look and feel, to replace Java antiquated looking default one. 
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()/* "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" */);
		}
		catch(Exception e)
		{
			System.out.println("Cannot find Look And Feel class name.");
		}
		
		// Getting the font from the file specified in the GUI\Fonts folder, if yo want to. Ended up deciding against it. 
		//Utils.getFont();
		
		// Getting the right size of the display to determine the size of the panel. 
		GraphicsDevice GD = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int displayWidth = GD.getDisplayMode().getWidth();
		int displayHeight = GD.getDisplayMode().getHeight();
		
		// Setting up the frame.
		myFrame.setTitle("NoOneNose - Code Smell Detector");
		myFrame.setIconImage((new ImageIcon("GUI\\Images\\Main_Icon.png")).getImage());
		myFrame.setSize(displayWidth / 2, displayHeight / 2);
		myFrame.setMinimumSize(new Dimension(displayWidth / 2, displayHeight / 2));
		
		// Setting up the main content panel of the frame. 
		myPanel.setBackground(new Color(25, 25, 25));
		myPanel.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 128), 2));
		myPanel.setLayout(new BorderLayout());
		
		// Components of UI so far. 
		CustomTabsPanel myTabPane = new CustomTabsPanel(true);
		CustomTabsPanel mainPanel = new CustomTabsPanel(false);
		AnalysisPanel analysisPanel = new AnalysisPanel();
		ReportPanel reportPanel = new ReportPanel();
		FileChooserPanel fileChooserPanel = new FileChooserPanel();
		SettingsPanel settingsPanel =  new SettingsPanel();
		
		// The welcome screen.
		WelcomeScreen welcomeScreen = new WelcomeScreen();
		
		// Setting up the action listener for the button that runs the analysis/detection. 
		analysisPanel.setupActionListener(fileChooserPanel, settingsPanel, reportPanel);
		
		// Adding the sub-tabs to the main analysis panel.
		mainPanel.addTab("Analysis", analysisPanel);
		mainPanel.addTab("Report", reportPanel);
		
		// Adding each component as a tab to the tabbed pane. 
		myTabPane.addTab("Analysis", mainPanel);
		myTabPane.addTab("Pick Files", fileChooserPanel);
		myTabPane.addTab("Settings", settingsPanel);
		
		// Adding the icons to each tab.
		ImageIcon analysisIcon = Utils.resizeIcon(new ImageIcon("GUI\\Images\\Analysis_Icon.png"), 40, 40);
		ImageIcon filesIcon = Utils.resizeIcon(new ImageIcon("GUI\\Images\\Folder_Icon.png"), 50, 40);
		ImageIcon settingsIcon = Utils.resizeIcon(new ImageIcon("GUI\\Images\\Settings_Icon.png"), 40, 40);
		
		myTabPane.setTabIcon(0, analysisIcon);
		myTabPane.setTabIcon(1, filesIcon);
		myTabPane.setTabIcon(2, settingsIcon);
		
		welcomeScreen.setupActionListener(myPanel, myTabPane);
		
		myPanel.add(welcomeScreen, BorderLayout.CENTER);
		
		myFrame.add(myPanel);
		myFrame.validate();
		myFrame.setVisible(true);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
 }