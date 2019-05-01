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
	private JButton startButton = new JButton("GO!");
	
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
		titleSubText.setText("Begin the analysis of your project from here. Press GO to begin.");
		titleSubText.setLineWrap(true);
        titleSubText.setWrapStyleWord(true);
        titleSubText.setEditable(false);
		mainTitle.add(titleSubText, BorderLayout.CENTER);
		
		startButton.setPreferredSize(new Dimension(200, 50));
		startButton.setFont(Utils.FONT);
		
		mainPanel.add(startButton);
		
		this.add(mainTitle, BorderLayout.NORTH);
		this.add(mainPanel, BorderLayout.CENTER);
	}
	
	public void setupActionListener(FileChooserPanel fcp)
	{
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
	}
	
}