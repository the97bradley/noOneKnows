package NoOneNose.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.ArrayList;

import NoOneNose.GUI.FileDrop.*;

public class FileChooserPanel extends JPanel
 {
	private JFileChooser fileChooser = new JFileChooser();
	private JFileChooser folderChooser = new JFileChooser();
	private FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Java source files (.java)", "java");
	private FileNameExtensionFilter folderFilter = new FileNameExtensionFilter("Select a Folder", "java");
	
	// Lists for storing files.
	private ArrayList<File> javaFileList = new ArrayList<File>();
	private ArrayList<File> classFileList = new ArrayList<File>();
	private ArrayList<File> tempFileList = new ArrayList<File>();
	
	public FileChooserPanel()
	{
		this.setBackground(new Color(50, 50, 50));
		this.setBorder(BorderFactory.createLineBorder(new Color(75, 75, 75), 1));
		
		JPanel filePanel = new JPanel();
		filePanel.setBorder(BorderFactory.createLineBorder(Color.CYAN, 1));
		//filePanel.setPreferredSize(new Dimension(400, 200));
		
		
		// ---
		// Setting up the drag and drop file listener.
		// ---
		new FileDrop(filePanel, new FileDrop.Listener()
        {   
			public void filesDropped(File[] files)
            {   
				for(File f : files)
                {
					if (Utils.getFileExtension(f).equals("java"))
					{
						javaFileList.add(f);
						System.out.println("Number of files in list: " + javaFileList.size());
					}
					else
					{
						System.out.println("Only JAVA source files m8!");
					}
                }
            }
        });
		
		// ---
		// Setting up the file chooser and button.
		// ---
		fileChooser.setFileFilter(fileFilter);
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		JButton button = new JButton("Select File(s)");
		button.setFocusPainted(false);
		button.addActionListener(new ActionListener()
		{  
			public void actionPerformed(ActionEvent e)
			{  
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION)
				{
					File[] files = fileChooser.getSelectedFiles();
					System.out.println("Chosen file: " + fileChooser.getSelectedFile().getName());
					for (File f : files)
					{
						javaFileList.add(f);
					}
					System.out.println("Number of files in list: " + javaFileList.size());
				}
			}  
		});  
		
		// ---
		// Setting up the directory chooser and button.
		// ---
		folderChooser.setFileFilter(folderFilter);
		folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		folderChooser.setMultiSelectionEnabled(false);
		folderChooser.setAcceptAllFileFilterUsed(false);
		
		JButton button1 = new JButton("Select Directory");
		button1.setFocusPainted(false);
		button1.addActionListener(new ActionListener()
		{  
			public void actionPerformed(ActionEvent e)
			{  
				int returnValue = folderChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION)
				{
					File file = folderChooser.getSelectedFile();
					System.out.println("Selected Folder = " + file.getName() + "\\");
					
					File[] files = getAllFilesFromDirectory(file);
					for (File f : files)
					{
						javaFileList.add(f);
					}
				}
			}  
		});  
		
		// ---
		// Setup for the layout.
		// ---
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 20, 5, 20);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.weightx = 3;
		gbc.ipady = 200;
		this.add(filePanel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.ipady = 50;
		this.add(button, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(button1, gbc);		
	}
	
	// Recursively get all files from the given directory, including sub-folders. 
	private File[] getAllFilesFromDirectory(File directory)
	{		
		File[] files = directory.listFiles();
		for (File f :  files)
		{
			if (f.isDirectory())
			{
				System.out.println("Directory: " + f.getName());
				getAllFilesFromDirectory(f);
			}
			else if (Utils.getFileExtension(f).equals("java"))
			{
				System.out.println("	File: " + f.getName());
				tempFileList.add(f);
			}
		}
		
		File[] toReturn = new File[tempFileList.size()];
		toReturn = tempFileList.toArray(toReturn);
		tempFileList.clear();
		
		return toReturn;
	}
 }