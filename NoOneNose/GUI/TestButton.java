package NoOneNose.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

public class  TestButton extends JButton
 {
	public TestButton(String name)
	{
		super(name);
		this.setFocusPainted(false);
		this.setPreferredSize(new Dimension(200, 50));
		this.setBackground(new Color(128, 128, 225));
		
		this.setFont(new Font("SST", Font.PLAIN, 15));
		
		this.setToolTipText("Button ToolTip");
		
		this.addActionListener(new ActionListener()
		{  
			public void actionPerformed(ActionEvent e)
			{  
				System.out.println("Button Clicked");
			}  
		});  
		
		
		this.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				
			}
			public void mouseEntered(MouseEvent e)
			{
				//System.out.println("Mouse Entered.");
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
		
	}

 }