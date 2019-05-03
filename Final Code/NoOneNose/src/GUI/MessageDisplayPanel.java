package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

public class MessageDisplayPanel
{
	private static JPanel mainPanel = new JPanel();
	private static JTextArea messagePanel = new JTextArea();
	
	public static JPanel messageDisplayPanelBuild()
	{
		mainPanel.setLayout(new GridBagLayout());
		mainPanel.setOpaque(false);
		
		messagePanel.setBackground(Color.BLACK);
		messagePanel.setForeground(Color.WHITE);
		messagePanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.WHITE));
		messagePanel.setFont(new Font("SST", Font.PLAIN, 15));
        messagePanel.setEditable(false);
		
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.ipadx = 250;
		constraints.ipady = 75;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(10, 0, 0, 0);
		mainPanel.add(messagePanel, constraints);
		
		return mainPanel;
	}
	
	public static void displayMessage(String text)
	{
		messagePanel.append(text);
	}
}