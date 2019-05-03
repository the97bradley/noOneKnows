package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Parent panel for displaying the report.
public class ReportPanel extends JPanel
{
	private GridBagConstraints gbc = new GridBagConstraints();
	private GridBagConstraints mainGBC = new GridBagConstraints();
	private ArrayList<Boolean> enabledSmells = new ArrayList<Boolean>();
	
	private JPanel reportDisplayPanel = new JPanel();
	private JTextArea reportTextArea = new JTextArea();
		
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
		mainTitleText.setFont(Utils.FONT.deriveFont(32f));;
		mainTitleText.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 0));
		mainTitle.add(mainTitleText, BorderLayout.NORTH);
		
		JTextArea titleSubText = new JTextArea();
		titleSubText.setOpaque(false);
		titleSubText.setForeground(Color.GRAY);
		titleSubText.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
		titleSubText.setFont(Utils.FONT.deriveFont(16f));
		titleSubText.setText("View the report generated from the last analysis.");
		titleSubText.setLineWrap(true);
        titleSubText.setWrapStyleWord(true);
        titleSubText.setEditable(false);
		mainTitle.add(titleSubText, BorderLayout.CENTER);
		
		reportDisplayPanel.setLayout(new BorderLayout());
		reportDisplayPanel.setBorder(BorderFactory.createLineBorder(Utils.subComponentBorderColour, 1));
		
		setupReportTextArea();

		mainGBC.fill = GridBagConstraints.VERTICAL;
		mainGBC.anchor = GridBagConstraints.LINE_START;
		mainGBC.weightx = 1.0;
		mainGBC.weighty = 1;
		mainGBC.ipadx = 500;
		mainGBC.insets = new Insets(40, 40, 40, 40);
		mainPanel.add(reportDisplayPanel, mainGBC);
		
		this.add(mainTitle, BorderLayout.NORTH);
		this.add(mainPanel, BorderLayout.CENTER);
	}
	
	private void setupReportTextArea()
	{
		reportTextArea.setBackground(Utils.subComponentColour);
		reportTextArea.setForeground(Utils.subComponentTitleColour);
		reportTextArea.setFont(Utils.FONT.deriveFont(18f));
		reportTextArea.setLineWrap(true);
        reportTextArea.setWrapStyleWord(true);
        reportTextArea.setEditable(false);
		
		JScrollPane reportTextAreaScroll = new JScrollPane(reportTextArea);
		reportTextAreaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  
		reportTextAreaScroll.setBorder(null);
		
		reportDisplayPanel.add(reportTextAreaScroll, BorderLayout.CENTER);
	}
	
	// Method for adding text to the report display area. 
	public void appendReportText(String text)
	{
		reportTextArea.append(text);
	}
	
	// Method for clearing the all text from the report display area. 
	public void clear()
	{
		reportTextArea.setText(null);
	}
	
}