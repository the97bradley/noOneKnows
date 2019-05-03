package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

// An individual tab from the custom tabbed pane. 
public class Tab
{
	private JPanel tab = new JPanel();
	private JLabel tabName = new JLabel();
	private JComponent tabContent;
	boolean isSelected = false;
	private int tabIndex = 0;
	
	// Colours.
	private Color tabColour = new Color(0, 0, 0);
	private Color selectedColour = new Color(200, 200, 200);
	private Color borderColour = new Color(255, 0, 0);
	
	public Tab(int index, String name, JComponent content)
	{
		tabIndex = index;
		tabName.setText(name);
		tabName.setForeground(Color.GRAY);
		
		tab.setLayout(new GridBagLayout());
		tab.setBackground(tabColour);
		tab.add(tabName);
		setupTabMouseListener();
		
		tabContent = content;
	}
	
	public void setupTabMouseListener()
	{
		tab.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				
			}
			public void mouseEntered(MouseEvent e)
			{
				if (!isSelected)
				{
					tab.setBorder(null);
					tab.setBackground(new Color(tabColour.getRed() + 50, tabColour.getGreen() + 50, tabColour.getBlue() + 50));
					tab.revalidate();
					tab.repaint();
				}
			}
			public void mouseExited(MouseEvent e)
			{
				if (!isSelected)
				{
					tab.setBorder(null);
					tab.setBackground(tabColour);
					tab.revalidate();
					tab.repaint();
				}
			}
			public void mousePressed(MouseEvent e)
			{
				
			}
			public void mouseReleased(MouseEvent e)
			{
				
			}
		});
	}
	
	// Set whether this tab is selected. 
	public void setSelected(boolean bool)
	{
		isSelected = bool;
		
		// Resetting the colours if gets deselected.
		if (isSelected)
		{
			tabName.setForeground(selectedColour);
			tab.setBackground(new Color(tabColour.getRed(), tabColour.getGreen(), tabColour.getBlue(), 128));
			tab.setBorder(BorderFactory.createLineBorder(selectedColour, 1));
			tab.revalidate();
			tab.repaint();
		}
		else
		{
			tabName.setForeground(Color.GRAY);
			tab.setBackground(tabColour);
			tab.setBorder(null);
			tab.revalidate();
			tab.repaint();
		}
	}
	
	public void setFontSize(float f)
	{
		tabName.setFont(Utils.FONT.deriveFont(f));
	}
	
	public void setIcon(ImageIcon icon)
	{
		tabName.setIcon(icon);
		tabName.revalidate();
		tabName.repaint();
	}
	
	public JPanel getTab()
	{
		return tab;
	}
	
	public JComponent getTabContent()
	{
		return tabContent;
	}
	
	public int getIndex()
	{
		return tabIndex;
	}
}