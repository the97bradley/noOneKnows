package NoOneNose.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ReportPanel extends JPanel
{
	private GridBagConstraints gbc = new GridBagConstraints();
	private GridBagConstraints mainGBC = new GridBagConstraints();
	private ArrayList<Boolean> enabledSmells = new ArrayList<Boolean>();
	
	private JPanel reportDisplay = new JPanel();
	private JTextArea reportTextArea = new JTextArea();
	
	private Color reportColour = new Color(40, 45, 60);
	private Color reportBorder = new Color(80, 90, 100);
	
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
		
		reportDisplay.setLayout(new BorderLayout());
		//reportDisplay.setBackground(reportColour);
		reportDisplay.setBorder(BorderFactory.createLineBorder(reportBorder, 1));
		
		setupReportTextArea();

		mainGBC.fill = GridBagConstraints.VERTICAL;
		mainGBC.anchor = GridBagConstraints.LINE_START;
		mainGBC.weightx = 1;
		mainGBC.weighty = 1;
		mainGBC.ipadx = 500;
		mainGBC.insets = new Insets(40, 40, 40, 40);
		mainPanel.add(reportDisplay, mainGBC);
		
		this.add(mainTitle, BorderLayout.NORTH);
		this.add(mainPanel, BorderLayout.CENTER);
	}
	
	private void setupReportTextArea()
	{
		reportTextArea.setBackground(reportColour);
		reportTextArea.setForeground(Color.WHITE);
		reportTextArea.setLineWrap(true);
        reportTextArea.setWrapStyleWord(true);
        reportTextArea.setEditable(false);
		
		JScrollPane reportTextAreaScroll = new JScrollPane(reportTextArea);
		reportTextAreaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  
		reportTextAreaScroll.setBorder(null);
		
		
		reportDisplay.add(reportTextAreaScroll, BorderLayout.CENTER);
	}
	
	public void appendReportText(String text)
	{
		reportTextArea.append(text);
	}
	
}