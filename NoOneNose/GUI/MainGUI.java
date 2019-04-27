package NoOneNose.GUI;

import javax.swing.*;
import java.awt.*;
import javax.swing.UIManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI
 {
	// Main window and it's content panel. Specifically using a panel rather than the frame's content pane
	// to make it easy to work with and swap between panels easily. 
	private JFrame myFrame = new JFrame();
	private JPanel myPanel = new JPanel();
	
	public void mainFrameBuild()
	{		
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()/* "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" */);
		}
		catch(Exception e)
		{
			System.out.println("Cannot find Look And Feel class name.");
		}
		
		// Getting the right size of the display to determine the size of the panel. 
		GraphicsDevice GD = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int displayWidth = GD.getDisplayMode().getWidth();
		int displayHeight = GD.getDisplayMode().getHeight();
		
		// Setting up the frame.
		myFrame.setTitle("NoOneNose - Smell Detection Yoke");
		myFrame.setSize(displayWidth / 2, displayHeight / 2);
		myFrame.setMinimumSize(new Dimension(displayWidth / 2, displayHeight / 2));
		
		// Setting up the main content panel of the frame. 
		myPanel.setBackground(new Color(25, 25, 25));
		myPanel.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 128), 2));
		myPanel.setLayout(new BorderLayout());
		
		// Components of UI so far. 
		CustomTabsPanel myTabPane = new CustomTabsPanel(true);
		CustomTabsPanel mainPanel = new CustomTabsPanel(false);
		AnalysisPanel analysis = new AnalysisPanel();
		JPanel messagePanel = MessageDisplayPanel.messageDisplayPanelBuild();
		FileChooserPanel fileChooserPanel = new FileChooserPanel();
		
		// -------------------
		// Testing random yokes
		// ------------------
		CustomTabsPanel testTabPane = new CustomTabsPanel(false);
		JPanel tempPanel02 = new JPanel();
		tempPanel02.setBackground(Color.CYAN);
		
		//BackgroundPanel background = new BackgroundPanel(new ImageIcon("NoOneNose\\GUI\\Images\\background.jpg").getImage(), BackgroundPanel.SCALED);
		
		//testTabPane.addTab("test", tempPanel02);
		//testTabPane.addTab("test part II", background);
		
		MessageDisplayPanel.displayMessage("Settings will be here!");
		// ------------------
		
		mainPanel.addTab("Analysis", analysis);
		mainPanel.addTab("Report", new JLabel("REPORT STUFF GOES HERE..."));
		
		// Adding each component as a tab to the tabbed pane. 
		myTabPane.addTab("Analysis", mainPanel);
		myTabPane.addTab("Pick Files", fileChooserPanel);
		myTabPane.addTab("Settings", messagePanel);
		
		myPanel.add(myTabPane, BorderLayout.CENTER);
		myFrame.add(myPanel);
		myFrame.validate();
		myFrame.setVisible(true);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void swapActivePanel(JComponent component)
	{
		myPanel.removeAll();
		myPanel.add(component, BorderLayout.CENTER);
		myPanel.revalidate();
		myPanel.repaint();
	}
 }