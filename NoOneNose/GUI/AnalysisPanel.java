package NoOneNose.GUI;

import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

public class AnalysisPanel extends JPanel
{
	public AnalysisPanel()
	{
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(25, 25, 25));
		this.setBorder(BorderFactory.createLineBorder(new Color(75, 75, 75), 1));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setOpaque(false);
		
		JPanel mainTitle= new JPanel();
		mainTitle.setLayout(new BorderLayout());
		mainTitle.setOpaque(false);
		mainTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(75, 75, 75)));
		
		JLabel mainTitleText = new JLabel("LAUNCH THE ANALYSIS");
		mainTitleText.setForeground(Color.WHITE);
		mainTitleText.setFont(Utils.FONT.deriveFont(32f));
		mainTitleText.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 0));
		mainTitle.add(mainTitleText, BorderLayout.NORTH);
		
		JTextArea titleSubText = new JTextArea();
		titleSubText.setOpaque(false);
		titleSubText.setForeground(Color.GRAY);
		titleSubText.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
		titleSubText.setFont(Utils.FONT.deriveFont(16f));
		titleSubText.setText("Sample text.");
		titleSubText.setLineWrap(true);
        titleSubText.setWrapStyleWord(true);
        titleSubText.setEditable(false);
		mainTitle.add(titleSubText, BorderLayout.CENTER);
		
		JButton startButton = new JButton("GO!");
		JButton settingsButton = new JButton(Utils.resizeIcon(new ImageIcon("NoOneNose\\GUI\\Images\\Icon_Settings.png"), 30, 30));
		
		startButton.setPreferredSize(new Dimension(200, 50));
		startButton.setFont(new Font("Brandon Text Light", Font.PLAIN, 15));
		settingsButton.setPreferredSize(new Dimension(50, 50));
		
		startButton.addActionListener(new ActionListener()
		{  
			public void actionPerformed(ActionEvent e)
			{  
				// ---
				// CODE GOES HERE DUDE
				// ---
				System.out.println("Let's go dude!");
			}  
		}); 

		mainPanel.add(settingsButton);
		mainPanel.add(startButton);
		
		this.add(mainTitle, BorderLayout.NORTH);
		this.add(mainPanel, BorderLayout.CENTER);
	}
	
}