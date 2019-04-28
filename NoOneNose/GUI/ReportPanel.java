package NoOneNose.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ReportPanel extends JPanel
{
	private GridBagConstraints gbc = new GridBagConstraints();
	private GridBagConstraints mainGBC = new GridBagConstraints();
	private ArrayList<Boolean> enabledSmells = new ArrayList<Boolean>();
	
	private Color smellPanelColour = new Color(40, 45, 60);
	private Color smellPanelBorder = new Color(80, 90, 100);
	
	public ReportPanel()
	{
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(25, 25, 25));
		this.setBorder(BorderFactory.createLineBorder(new Color(75, 75, 75), 1));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		mainPanel.setOpaque(false);
		
		JPanel mainTitle= new JPanel();
		mainTitle.setLayout(new BorderLayout());
		mainTitle.setOpaque(false);
		mainTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(75, 75, 75)));
		
		JLabel mainTitleText = new JLabel("PROJECT REPORT");
		mainTitleText.setForeground(Color.WHITE);
		mainTitleText.setFont(new Font("Brandon Text Light", Font.PLAIN, 32));
		mainTitleText.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 0));
		mainTitle.add(mainTitleText, BorderLayout.NORTH);
		
		JTextArea titleSubText = new JTextArea();
		titleSubText.setOpaque(false);
		titleSubText.setForeground(Color.GRAY);
		titleSubText.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
		titleSubText.setFont(new Font("Brandon Text Light", Font.PLAIN, 16));
		titleSubText.setText("More sample text.");
		titleSubText.setLineWrap(true);
        titleSubText.setWrapStyleWord(true);
        titleSubText.setEditable(false);
		mainTitle.add(titleSubText, BorderLayout.CENTER);

		
		this.add(mainTitle, BorderLayout.NORTH);
		this.add(mainPanel, BorderLayout.CENTER);
	}
	
}