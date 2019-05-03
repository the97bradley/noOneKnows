package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// A simple screen that welcomes the user and gives and bit of general and usage information. 
public class WelcomeScreen extends JPanel
{
	private GridBagConstraints gbc = new GridBagConstraints();
	
	private JButton continueButton = new JButton("Continue");

	public WelcomeScreen()
	{
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(25, 25, 25));
		this.setBorder(BorderFactory.createLineBorder(new Color(75, 75, 75), 1));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		mainPanel.setOpaque(false);
		
		JPanel mainTitle = new JPanel();
		mainTitle.setLayout(new BorderLayout());
		mainTitle.setOpaque(false);
		mainTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(75, 75, 75)));
		
		JLabel mainTitleText = new JLabel("Welcome To NoOneNose");
		mainTitleText.setForeground(Color.WHITE);
		mainTitleText.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		mainTitleText.setFont(Utils.FONT.deriveFont(36f));
		mainTitle.add(mainTitleText, BorderLayout.CENTER);
		
		JLabel mainTitleIcon = new JLabel(Utils.resizeIcon(new ImageIcon("GUI\\Images\\Main_Icon.png"), 80, 80));
		mainTitleIcon.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		mainTitle.add(mainTitleIcon, BorderLayout.WEST);
		
		JPanel generalPanel = createInfoPanel
		(
			"General", 
			"NoOneNose is a code smell detector created by Bradley Schwartz, Chris Gibney Finglas, Ronan Merriman, & Zheyuan Zhang.\n\n" +
			"It is capable of detecting a variety of code smells within a given code base. Both the input code base and the detectable code smells can be customized.\n\n" +
			"A report is then generated detailing the smells detected and their location.\n\n"			
		);
		JPanel usagePanel = createInfoPanel
		(	
			"Usage", 
			"- Add files in the 'Pick Your Files' tab.\n\n" +
			"- Select 'GO' from the 'Analysis' tab to run the analysis of your chosen code.\n\n" +
			"- View the report in 'Report' section of the Analysis tab.\n\n" +
			"- Adjust smell settings in the 'Settings' tab."
		);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		buttonPanel.setOpaque(false);
		
		continueButton.setFont(Utils.FONT.deriveFont(18f));
		continueButton.setPreferredSize(new Dimension(150, 60));
		gbc.insets = new Insets(15, 15, 15, 15);
		buttonPanel.add(continueButton, gbc);
		
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.insets = new Insets(20, 20, 20, 20);
		gbc.ipadx = 200;
		mainPanel.add(generalPanel, gbc);
		gbc.gridx = 1;
		mainPanel.add(usagePanel, gbc);
		
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.add(mainTitle, BorderLayout.NORTH);
		this.add(mainPanel, BorderLayout.CENTER);
	}
	
	private JPanel createInfoPanel(String title, String body)
	{
		JPanel thePanel = new JPanel();
		thePanel.setLayout(new BorderLayout());
		thePanel.setBackground(Utils.subComponentColour);
		thePanel.setBorder(BorderFactory.createLineBorder(Utils.subComponentBorderColour, 1));
		
		JLabel titleText = new JLabel(title);
		titleText.setFont(Utils.FONT.deriveFont(24f));
		titleText.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		titleText.setForeground(Utils.subComponentTitleColour);
		
		JTextArea titleSubText = new JTextArea();
		titleSubText.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
		titleSubText.setBackground(Utils.subComponentColour);
		titleSubText.setForeground(Utils.subComponentTextColour);
		titleSubText.setFont(Utils.FONT.deriveFont(16f));
		titleSubText.setLineWrap(true);
        titleSubText.setWrapStyleWord(true);
        titleSubText.setEditable(false);
		titleSubText.setText(body);
		
		JScrollPane textScroll = new JScrollPane(titleSubText);
		textScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  
		textScroll.setBorder(null);
		
		thePanel.add(titleText, BorderLayout.NORTH);
		thePanel.add(textScroll, BorderLayout.CENTER);
		
		return thePanel;
	}
	
	// Method for setting up the action listener. Intended to swap from the welcome screen to the main screen, after pressing.
	public void setupActionListener(JPanel p, JComponent c)
	{
		continueButton.addActionListener(new ActionListener()
		{  
			public void actionPerformed(ActionEvent e)
			{  
				p.removeAll();
				p.add(c, BorderLayout.CENTER);
				p.revalidate();
				p.repaint();
			}  
		}); 
	}
}