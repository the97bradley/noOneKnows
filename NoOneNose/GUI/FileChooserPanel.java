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
	 // Separate file choosers and appropriate filters. 
	private JFileChooser fileChooser = new JFileChooser();
	private JFileChooser folderChooser = new JFileChooser();
	private FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Java source files (.java)", "java");
	private FileNameExtensionFilter folderFilter = new FileNameExtensionFilter("Select a Folder", "java");
	
	// Lists for storing files.
	private ArrayList<File> javaFileList = new ArrayList<File>();
	private ArrayList<File> classFileList = new ArrayList<File>();
	private ArrayList<File> tempFileList = new ArrayList<File>();
	
	private JPanel filePanel = new JPanel();
	
	// Active file type we want to use. 
	private final String FILE_TYPE = "java";
	
	public FileChooserPanel()
	{
		this.setBackground(new Color(25, 25, 25));
		this.setBorder(BorderFactory.createLineBorder(new Color(75, 75, 75), 1));
		
		filePanel.setBorder(BorderFactory.createLineBorder(Color.CYAN, 1));
		filePanel.setLayout(new GridBagLayout());
		
		// Setting up the drag and drop file listener. Accepts single files, multiple files, and folders. 
		new FileDrop(filePanel, new FileDrop.Listener()
        {   
			public void filesDropped(File[] files)
            {   
				for(File f : files)
                {
					if (f.isDirectory())
					{
						File[] subFiles = getAllFilesFromDirectory(f, FILE_TYPE);
						for (File subFile : subFiles)
						{
							javaFileList.add(subFile);
							System.out.println("File added: " + subFile.getName());
						}
					}
					else if (Utils.getFileExtension(f).equals(FILE_TYPE))
					{
						javaFileList.add(f);
						System.out.println("File added: " + f.getName());
					}
                }
				System.out.println("Number of files in list: " + javaFileList.size());
            }
        });
		
		// Setting up the file chooser and button. Accepts .java files as per the filter. 
		fileChooser.setFileFilter(fileFilter);
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		JButton fileButton = new JButton("Select File(s)");
		fileButton.setFocusPainted(false);
		fileButton.addActionListener(new ActionListener()
		{  
			public void actionPerformed(ActionEvent e)
			{  
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION)
				{
					File[] files = fileChooser.getSelectedFiles();
					for (File f : files)
					{
						javaFileList.add(f);
						System.out.println("File added: " + f.getName());
						//addFileVisual(f);
					}
					System.out.println("Number of files in list: " + javaFileList.size());
				}
			}  
		});  
		
		// Setting up the directory chooser and button. Accepts folders/directories. 
		folderChooser.setFileFilter(folderFilter);
		folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		folderChooser.setMultiSelectionEnabled(false);
		folderChooser.setAcceptAllFileFilterUsed(false);
		
		JButton folderButton = new JButton("Select Directory");
		folderButton.setFocusPainted(false);
		folderButton.addActionListener(new ActionListener()
		{  
			public void actionPerformed(ActionEvent e)
			{  
				int returnValue = folderChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION)
				{
					File file = folderChooser.getSelectedFile();
					System.out.println("Selected Folder: " + file.getName() + "\\");
					
					File[] files = getAllFilesFromDirectory(file, FILE_TYPE);
					for (File f : files)
					{
						javaFileList.add(f);
						System.out.println("File added: " + f.getName());
					}
				}
				System.out.println("Number of files in list: " + javaFileList.size());
			}  
		});  
		
		JButton removeFileButton = new JButton("X");
		removeFileButton.setFocusPainted(false);
		removeFileButton.addActionListener(new ActionListener()
		{  
			public void actionPerformed(ActionEvent e)
			{  
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION)
				{
					File[] files = fileChooser.getSelectedFiles();
					for (File f : files)
					{
						removeFile(f.getName());
					}
					System.out.println("Number of files in list: " + javaFileList.size());
				}
			}  
		});  
		
		// Setup for the layout.
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(20, 20, 20, 20);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.ipady = 350;
		this.add(filePanel, gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.ipadx = 50;
		gbc.ipady = 30;
		this.add(fileButton, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(folderButton, gbc);		
		
		gbc.gridx = 2;
		this.add(removeFileButton, gbc);
	}
	
	// Method for removing a file from the list of files.
	private void removeFile(String name)
	{
		// Using a regular for loop to avoid the 'ConcurrentModificationException' when removing elements from an array list. 
		for (int i = 0; i < javaFileList.size(); i++)
		{
			System.out.println("'i': " + i);
			if (javaFileList.get(i).getName().equals(name))
			{
				javaFileList.remove(javaFileList.get(i));
				System.out.println("File removed: " + javaFileList.get(i).getName());
			}
		}
	}
	
	// Recursively get all files from the given directory, including sub-folders. 
	private File[] getAllFilesFromDirectory(File directory, String type)
	{		
		File[] files = directory.listFiles();
		System.out.println("Root folder: " + directory.getName());
		for (File f :  files)
		{
			if (f.isDirectory())
			{
				System.out.println("Entered folder: " + f.getName() + "\\");
				getAllFilesFromDirectory(f, type);
			}
			else if (Utils.getFileExtension(f).equals(type))
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
	
	private void addFileVisual(File file)
	{
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.ipadx = 75;
		gbc.ipady = 75;
		gbc.weighty = 1;
		gbc.gridx = javaFileList.size() - 1;
		
		FileVisual fileVisual = new FileVisual(file);
		
		filePanel.add(fileVisual, gbc);
		filePanel.revalidate();
		filePanel.repaint();
	}
	
	// Accessor for the added files.
	public ArrayList<File> getFileList()
	{
		return javaFileList;
	}
	
 }