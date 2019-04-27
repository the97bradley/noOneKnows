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
		this.setBackground(new Color(25, 25, 25));
		this.setBorder(BorderFactory.createLineBorder(new Color(75, 75, 75), 1));
		
		JButton startButton = new JButton("GO!");
		JButton settingsButton = new JButton(Utils.resizeIcon(new ImageIcon("NoOneNose\\GUI\\Images\\Icon_Settings.png"), 30, 30));
		
		startButton.setPreferredSize(new Dimension(200, 50));
		startButton.setFont(new Font("SST", Font.PLAIN, 15));
		settingsButton.setPreferredSize(new Dimension(50, 50));
		
		startButton.addActionListener(new ActionListener()
		{  
			public void actionPerformed(ActionEvent e)
			{  
				// ---
				// CODE GOES HERE DUDE
				// ---
			}  
		}); 

		this.add(settingsButton);
		this.add(startButton);
	}
	
}