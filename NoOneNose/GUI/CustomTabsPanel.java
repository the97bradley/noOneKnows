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
	private JPanel contentPanel = new JPanel();
	private GridBagConstraints gbc = new GridBagConstraints();
	private ArrayList<Tab> tabs = new ArrayList<Tab>();
	
	private int numberOfTabs = 0;
	private int currentTab = 0;
	boolean alignedLeft =false;
		
	public CustomTabsPanel(boolean left)
	{
		this.setLayout(new BorderLayout());
		this.setBackground(Color.RED);
		this.setOpaque(false);
		
		tabListPanel.setBackground(Color.GREEN);
		tabListPanel.setOpaque(false);
		tabListPanel.setLayout(new GridBagLayout());
		contentPanel.setBackground(Color.BLUE);
		contentPanel.setLayout(new BorderLayout());
		
		// Added boolean parameter to the constructor so you can decide whether you want the list of tabs
		// to be on the left (like I previously had them by default) or at the top. Added mainly to incorporate the
		// need for any extra tabs within tabs. 
		if (left)
		{
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridx = 0;
			gbc.ipadx = 200;
			gbc.ipady = 50;
			gbc.anchor = GridBagConstraints.PAGE_START;
			
			alignedLeft = true;
			this.add(tabListPanel, BorderLayout.WEST);
		}
		else
		{
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridy = 0;
			gbc.ipadx = 75;
			gbc.ipady = 25;
			gbc.anchor = GridBagConstraints.PAGE_START;
			
			this.add(tabListPanel, BorderLayout.NORTH);
		}
		
		this.add(contentPanel, BorderLayout.CENTER);
	}
	
	public void addTab(String tabText, JComponent tabContentPanel)
	{
		Tab tab = new Tab(tabText, tabContentPanel);
		
		int tabIndex = numberOfTabs;
		numberOfTabs++;
		
		// Setting default active tab, in this case the initial one. 
		if (tabIndex == 0)
		{
			tab.setSelected(true);
			contentPanel.add(tabContentPanel, BorderLayout.CENTER);
		}
		
		tab.getTab().addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				if (currentTab != tabIndex)
				{
					// Update current selected tab. 
					tabs.get(currentTab).setSelected(false);
					currentTab = tabIndex;
					tab.setSelected(true);
					
					// Update the current displayed tab. 
					contentPanel.removeAll();
					contentPanel.add(tab.getTabContent(), BorderLayout.CENTER);
					contentPanel.revalidate();	
					contentPanel.repaint();
				}
				else
				{
					System.out.println("Tab already selected!");
				}
			}
		});
		
		// Adding the tabs according to the right alignment.
		if (alignedLeft)
		{
			gbc.gridy = tabIndex;
			tabListPanel.add(tab.getTab(), gbc);
		}
		else
		{
			gbc.gridx = tabIndex;
			tabListPanel.add(tab.getTab(), gbc);
		}
		
		tabs.add(tab);
		this.revalidate();
		this.repaint();
	}
	
	/*
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
	*/
}