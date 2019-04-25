package NoOneNose.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class CustomTabsPanel extends JPanel
 {
	private JPanel tabListPanel = new JPanel();
	private JLayeredPane defaultContentPanel = new JLayeredPane();
	private GridBagConstraints listConstraints = new GridBagConstraints();
	private ArrayList<JComponent> tabs = new ArrayList<JComponent>();
	private ArrayList<JComponent> contentTabs = new ArrayList<JComponent>();
	
	private int numberOfTabs = 0;
	private int currentTab = 0;
	
	private final Color defaultTabColour = new Color(50, 50, 50);
	private final Color defaultTabBorder = new Color(100, 100, 100);
	private final Color unselectedTabColour = new Color(50, 50, 50);
	private final Color tabClickColour = new Color(50, 50, 50);
	
	public CustomTabsPanel()
	{
		this.setLayout(new BorderLayout());
		this.setBackground(Color.RED);
		
		listConstraints.fill = GridBagConstraints.HORIZONTAL;
		listConstraints.gridx = 0;
		listConstraints.ipadx = 200;
		listConstraints.ipady = 50;
		listConstraints.anchor = GridBagConstraints.PAGE_START;
		
		tabListPanel.setBackground(Color.GREEN);
		tabListPanel.setLayout(new GridBagLayout());
		defaultContentPanel.setBackground(Color.BLUE);
		defaultContentPanel.setLayout(new BorderLayout());
		
		this.add(tabListPanel, BorderLayout.WEST);
		this.add(defaultContentPanel, BorderLayout.CENTER);
	}
	
	public void addTab(String tabText, JComponent tabContentPanel)
	{
		JLabel tabName = new JLabel(tabText);
		JPanel tab = new JPanel();
		int tabIndex = numberOfTabs;
		
		numberOfTabs++;
		currentTab = tabIndex;
		
		contentTabs.add(tabContentPanel);
		defaultContentPanel.add(tabContentPanel, BorderLayout.CENTER);
		
		//tabName.setFont(new Font("SST", Font.PLAIN, 25));
		tabName.setForeground(Color.GRAY);
		
		tab.setLayout(new GridBagLayout());
		tab.setBackground(Color.BLACK);
		tab.add(tabName);
		
		tab.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				if (currentTab != tabIndex)
				{		
					// Reset the colours on the previously selected tab. 
					tabs.get(currentTab).setBackground(Color.BLACK);
					tabs.get(currentTab).setForeground(Color.GRAY);
					tabs.get(currentTab).setBorder(null);
					tabs.get(currentTab).getComponent(0).setForeground(Color.GRAY);
			
					currentTab = tabIndex;
					
					tab.setBackground(new Color(50, 50, 50));
					//tab.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
					tab.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, new Color(100, 100, 100)));
					tabName.setForeground(Color.WHITE);
					
					// Update the current displayed tab. 
					defaultContentPanel.removeAll();
					defaultContentPanel.add(contentTabs.get(tabIndex), BorderLayout.CENTER);
					defaultContentPanel.repaint();
					defaultContentPanel.revalidate();
				}
				else
				{
					System.out.println("Tab already selected!");
				}
			}
			public void mouseEntered(MouseEvent e)
			{
				//System.out.println("Mouse Entered Tab: " + tabName.getText());
			}
			public void mouseExited(MouseEvent e)
			{
				
			}
			public void mousePressed(MouseEvent e)
			{
				
			}
			public void mouseReleased(MouseEvent e)
			{
				
			}
		});
		
		listConstraints.gridy = tabIndex;
		
		tabs.add(tab);
		tabListPanel.add(tab, listConstraints);
		this.revalidate();
	}
	
	public void removeTab(int index)
	{
		tabListPanel.remove(tabs.get(index));
		defaultContentPanel.remove(contentTabs.get(index));
		tabs.remove(index);
		contentTabs.remove(index);
		
		tabListPanel.revalidate();
		tabListPanel.repaint();
		defaultContentPanel.revalidate();
		defaultContentPanel.repaint();
	}
}