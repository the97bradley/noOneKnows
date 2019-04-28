package NoOneNose.GUI;

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

public class SettingsPanel extends JPanel
{
	private GridBagConstraints gbc = new GridBagConstraints();
	private GridBagConstraints mainGBC = new GridBagConstraints();
	private ArrayList<Boolean> enabledSmells = new ArrayList<Boolean>();
	
	private Color smellPanelColour = new Color(40, 45, 60);
	private Color smellPanelBorder = new Color(80, 90, 100);
	
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
		mainTitleText.setFont(new Font("Brandon Text Light", Font.PLAIN, 32));
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
		smellPanel.setBackground(smellPanelColour);
		smellPanel.setBorder(BorderFactory.createLineBorder(smellPanelBorder, 1));
		smellPanel.setPreferredSize(new Dimension(300, 400));
		
		JLabel titleText = new JLabel("Smell Settings");
		titleText.setFont(titleText.getFont().deriveFont(18f));
		titleText.setForeground(new Color(smellPanelBorder.getRed() + 100, smellPanelBorder.getGreen() + 100, smellPanelBorder.getBlue() + 100));
		
		JTextArea titleSubText = new JTextArea();
		titleSubText.setBackground(smellPanelColour);
		titleSubText.setForeground(new Color(smellPanelBorder.getRed() + 75, smellPanelBorder.getGreen() + 75, smellPanelBorder.getBlue() + 75));
		titleSubText.setFont(titleText.getFont().deriveFont (14f));
		titleSubText.setText("Adjust which code smells the application will search for within your project.");
		titleSubText.setLineWrap(true);
        titleSubText.setWrapStyleWord(true);
        titleSubText.setEditable(false);
		
		JPanel title = new JPanel();
		title.setLayout(new BorderLayout());
		title.setBackground(smellPanelColour);
		title.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, smellPanelBorder));
		title.add(titleText, BorderLayout.NORTH);
		title.add(titleSubText, BorderLayout.CENTER);

		JPanel smellsList = new JPanel();
		smellsList.setLayout(new GridBagLayout());
		smellsList.setBackground(smellPanelColour);
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
			smellCheckBox.setForeground(Color.WHITE);
			
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