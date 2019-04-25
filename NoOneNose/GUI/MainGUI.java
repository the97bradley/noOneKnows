package NoOneNose.GUI;

import javax.swing.*;
import java.awt.*;
import javax.swing.UIManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI
 {
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
		
		//UIManager.getDefaults().put("TabbedPane.contentBorderInsets", new Insets(0,0,0,0));
		
		GraphicsDevice GD = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int displayWidth, displayHeight;
		
		displayWidth = GD.getDisplayMode().getWidth();
		displayHeight = GD.getDisplayMode().getHeight();
		
		JFrame myFrame = new JFrame();
		
		myFrame.setTitle("Testing Swing");
		myFrame.setSize(displayWidth / 2, displayHeight / 2);
		myFrame.setMinimumSize(new Dimension(displayWidth / 2, displayHeight / 2));
		myFrame.setVisible(true);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel myPanel = new JPanel();
		
		myPanel.setBackground(new Color(25, 25, 25));
		myPanel.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 128), 2));
		myPanel.setLayout(new BorderLayout());
		
		JTextArea mySubPanel = new JTextArea();
		CustomTabsPanel myTabPane = new CustomTabsPanel();
		AnalysisPanel mainPanel = new AnalysisPanel();
		JPanel messagePanel = MessageDisplayPanel.messageDisplayPanelBuild();
		
		mySubPanel.setBackground(new Color(50, 50, 75));
		mySubPanel.setForeground(new Color(25, 25, 25));
		mySubPanel.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 150), 1));
		mySubPanel.setFont(new Font("SST", Font.PLAIN, 15));
		
		FileChooserPanel fileChooserPanel = new FileChooserPanel();
		JPanel tempPanel02 = new JPanel();
		tempPanel02.setBackground(Color.CYAN);
		
		myTabPane.addTab("Analysis", mainPanel);
		myTabPane.addTab("Pick FIles", fileChooserPanel);
		myTabPane.addTab("Settings", messagePanel);
		
		myPanel.add(myTabPane, BorderLayout.CENTER);
		
		myFrame.add(myPanel);
		myFrame.validate();
	}
 }