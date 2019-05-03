package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

// Parent panel for the smell settings.
public class SettingsPanel extends JPanel
{
	private GridBagConstraints gbc = new GridBagConstraints();
	private GridBagConstraints mainGBC = new GridBagConstraints();
	private ArrayList<Boolean> enabledSmells = new ArrayList<Boolean>();
	
	public SettingsPanel()
	{
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(25, 25, 25));
		this.setBorder(BorderFactory.createLineBorder(new Color(75, 75, 75), 1));
		
		JPanel mainTitle = new JPanel();
		mainTitle.setLayout(new BorderLayout());
		mainTitle.setOpaque(false);
		mainTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(75, 75, 75)));
		JLabel mainTitleText = new JLabel("SETTINGS");
		mainTitleText.setForeground(Color.WHITE);
		mainTitleText.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		mainTitleText.setFont(Utils.FONT.deriveFont(32f));
		mainTitle.add(mainTitleText, BorderLayout.WEST);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setOpaque(false);
		mainPanel.setLayout(new GridBagLayout());
		
		JPanel smellSettingsPanel = createSmellSettingsPanel();
		
		mainGBC.fill = GridBagConstraints.VERTICAL;
		mainGBC.anchor = GridBagConstraints.LINE_START;
		mainGBC.weightx = 1;
		mainGBC.insets = new Insets(0, 40, 0, 40);
		mainPanel.add(smellSettingsPanel, mainGBC);
		
		this.add(mainTitle, BorderLayout.NORTH);
		this.add(mainPanel, BorderLayout.CENTER);
	}
	
	private JPanel createSmellSettingsPanel()
	{
		JPanel smellPanel = new JPanel();
		smellPanel.setLayout(new BorderLayout());
		smellPanel.setBackground(Utils.subComponentColour);
		smellPanel.setBorder(BorderFactory.createLineBorder(Utils.subComponentBorderColour, 1));
		smellPanel.setPreferredSize(new Dimension(300, 400));
		
		JLabel titleText = new JLabel("Smell Settings");
		titleText.setFont(Utils.FONT.deriveFont(18f));
		titleText.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		titleText.setForeground(Utils.subComponentTitleColour);
		
		JTextArea titleSubText = new JTextArea();
		titleSubText.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		titleSubText.setBackground(Utils.subComponentColour);
		titleSubText.setForeground(Utils.subComponentTextColour);
		titleSubText.setFont(Utils.FONT.deriveFont(16f));
		titleSubText.setText("Adjust which code smells the application will search for within your project.");
		titleSubText.setLineWrap(true);
        titleSubText.setWrapStyleWord(true);
        titleSubText.setEditable(false);
		
		JPanel title = new JPanel();
		title.setLayout(new BorderLayout());
		title.setBackground(Utils.subComponentColour);
		title.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Utils.subComponentBorderColour));
		title.add(titleText, BorderLayout.NORTH);
		title.add(titleSubText, BorderLayout.CENTER);

		JPanel smellsList = new JPanel();
		smellsList.setLayout(new GridBagLayout());
		smellsList.setBackground(Utils.subComponentColour);
		addSmellsList(smellsList);
		addFiller(smellsList);
		
		JScrollPane smellsListScroll = new JScrollPane(smellsList);
		smellsListScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  
		//smellsListScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
		smellsListScroll.setBorder(null);
		
		smellPanel.add(title, BorderLayout.NORTH);
		smellPanel.add(smellsListScroll, BorderLayout.CENTER);
		return smellPanel;
	}
	
	// Adds to the panel the list of check boxes for each smell and enables the ability
	// to enable/disable each one. 
	private void addSmellsList(JPanel panel)
	{
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.weightx = 1;
		
		int gridy = 0;
		for (String s : Utils.SMELL_LIST)
		{
			JCheckBox smellCheckBox = new JCheckBox(s, true);
			smellCheckBox.setOpaque(false);
			smellCheckBox.setForeground(Utils.subComponentTextColour);
			smellCheckBox.setFont(Utils.FONT.deriveFont(14f));
			
			enabledSmells.add(true);
			
			int index = gridy;
			
			smellCheckBox.addItemListener(new ItemListener()
			{    
				public void itemStateChanged(ItemEvent e) 
				{
					if (e.getStateChange() == 1)
					{
						enabledSmells.set(index, true);
					}
					else
					{
						enabledSmells.set(index, false);
					}
					System.out.println( s + ": "  + enabledSmells.get(index));    
				}    
          }); 
			
			gbc.gridy = gridy;
			panel.add(smellCheckBox, gbc);
			gridy++;
		}
		
	}
	// Adds an addition invisible component with a greater y weight than the rest. 
	// This ensures the visible components are grouped towards the top of the
	// list panel. 
	private void addFiller(JPanel panel)
	{
		JPanel filler = new JPanel();
		filler.setOpaque(false);
		
		gbc.gridy = Utils.SMELL_LIST.length + 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.SOUTH;
		
		panel.add(filler, gbc);
	}
	
	public ArrayList<Boolean> getEnabledSmells()
	{
		return enabledSmells;
	}
}