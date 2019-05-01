package NoOneNose.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.IOException;
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
	private ArrayList<File> tempFileList = new ArrayList<File>();
	private ArrayList<String> excludedFolders = new ArrayList<String>();
	
	private String projectDirectoryName = "";
	
	private JPanel filePanel = new JPanel();
	
	private Color filePanelColour = new Color(40, 45, 60);
	private Color filePanelBorder = new Color(80, 90, 100);
	
	// Active file type we want to use. 
	private final String FILE_TYPE = "java";
	
	public FileChooserPanel()
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
		
		JLabel mainTitleText = new JLabel("PICK YOUR FILES");
		mainTitleText.setForeground(Color.WHITE);
		//mainTitleText.setFont(new Font("Brandon Text Light", Font.PLAIN, 32));
		mainTitleText.setFont(Utils.FONT.deriveFont(32f));
		mainTitleText.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 0));
		mainTitle.add(mainTitleText, BorderLayout.NORTH);
		
		JTextArea titleSubText = new JTextArea();
		titleSubText.setOpaque(false);
		titleSubText.setForeground(Color.GRAY);
		titleSubText.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
		//titleSubText.setFont(new Font("Brandon Text Light", Font.PLAIN, 16));
		titleSubText.setFont(Utils.FONT.deriveFont(16f));
		titleSubText.setText("Manage your source for the analysis. Here you can select your project's directory, add files individually, remove any files, as well as exclude a given directory from the analysis.");
		titleSubText.setLineWrap(true);
        titleSubText.setWrapStyleWord(true);
        titleSubText.setEditable(false);
		mainTitle.add(titleSubText, BorderLayout.CENTER);
		
		filePanel.setBackground(filePanelColour);
		filePanel.setBorder(BorderFactory.createLineBorder(filePanelBorder, 1));
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
						try
						{
							projectDirectoryName = f.getCanonicalPath();
						}
						catch (IOException exception)
						{
							exception.printStackTrace();
						}
						
						File[] subFiles = getAllFilesFromDirectory(f, FILE_TYPE);
						for (File subFile : subFiles)
						{
							javaFileList.add(subFile);
							System.out.println("File added: " + subFile.getName());
						}
					}
					else if (Utils.getFileExtension(f).equals(FILE_TYPE) && !checkForDuplicate(f))
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
						if (/*Utils.getFileExtension(f).equals(FILE_TYPE) && */ !checkForDuplicate(f))
						{
							javaFileList.add(f);
							System.out.println("File added: " + f.getName());
							
							//addFileVisual(f);
						}
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
					
					if (!checkForExclusion(file))
					{
						try
						{
							projectDirectoryName = file.getCanonicalPath();
						}
						catch (IOException exception)
						{
							exception.printStackTrace();
						}
						
						File[] files = getAllFilesFromDirectory(file, FILE_TYPE);
						for (File f : files)
						{
							if (/*Utils.getFileExtension(f).equals(FILE_TYPE) && */ !checkForDuplicate(f))
							{
								javaFileList.add(f);
								System.out.println("File added: " + f.getName());
							}
						}
					}
				}
				System.out.println("Number of files in list: " + javaFileList.size());
			}  
		});  
		
		JButton removeFileButton = new JButton(Utils.resizeIcon(new ImageIcon("NoOneNose\\GUI\\Images\\File_Remove_Icon.png"), 30, 40));
		removeFileButton.setContentAreaFilled(false);
		removeFileButton.setOpaque(false);
		removeFileButton.setToolTipText("Remove a file.");
		//removeFileButton.setFocusPainted(false);
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
		
		JButton excludeButton = new JButton(Utils.resizeIcon(new ImageIcon("NoOneNose\\GUI\\Images\\Folder_Exclude_Icon.png"), 50, 40));
		excludeButton.setContentAreaFilled(false);
		excludeButton.setOpaque(false);
		excludeButton.setToolTipText("Exclude a folder from selection.");
		excludeButton.addActionListener(new ActionListener()
		{  
			public void actionPerformed(ActionEvent e)
			{  
				int returnValue = folderChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION)
				{
					File file = folderChooser.getSelectedFile();
					System.out.println("Selected Folder for exclusion: " + file.getName() + "\\");
					
					excludedFolders.add(file.getName());
				}
			}
		});  
		
		// Setting up the layout for the buttons specifically. 
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.setOpaque(false);
		buttonPanel.setPreferredSize(new Dimension(700, 60));
		
		JPanel buttonSubPanel01 = new JPanel();
		JPanel buttonSubPanel02 = new JPanel();
		
		buttonSubPanel01.setLayout(new GridLayout(0, 2));
		buttonSubPanel01.setBackground(Color.RED);
		buttonSubPanel02.setLayout(new GridLayout(0, 2));
		buttonSubPanel02.setOpaque(false);
		
		fileButton.setPreferredSize(new Dimension(100, 60));
		folderButton.setPreferredSize(new Dimension(100, 60));
		removeFileButton.setPreferredSize(new Dimension(60, 60));
		excludeButton.setPreferredSize(new Dimension(60, 60));
		
		buttonSubPanel01.add(fileButton);
		buttonSubPanel01.add(folderButton);
		buttonSubPanel02.add(removeFileButton);
		buttonSubPanel02.add(excludeButton);
		
		buttonPanel.add(buttonSubPanel01, BorderLayout.WEST);
		buttonPanel.add(buttonSubPanel02, BorderLayout.EAST);
		
		// Setup for the layout.
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(20, 20, 20, 20);
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.ipadx = 400;
		gbc.ipady = 250;
		mainPanel.add(filePanel, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		//gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridy = 1;
		gbc.ipady = 25;
		gbc.ipadx = 60;
		//gbc.weightx = 1;
		mainPanel.add(buttonPanel, gbc);
		
		gbc.gridy = 2;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.SOUTH;
		
		JPanel filler = new JPanel();
		filler.setOpaque(false);
		mainPanel.add(filler, gbc);
		
		this.add(mainTitle, BorderLayout.NORTH);
		this.add(mainPanel, BorderLayout.CENTER);
	}
	
	// Accessor for the added files.
	public ArrayList<File> getFileList()
	{
		return javaFileList;
	}
	
	public String getProjectDirectoryName()
	{
		return projectDirectoryName;
	}
	
	public String[] getFileLocations()
	{
		ArrayList<String> tempStringList = new ArrayList<String>();
		
		try
		{
			for (File f : javaFileList)
			{
				tempStringList.add(f.getCanonicalPath());
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		String[] toReturn = new String[tempStringList.size()];
		toReturn = tempStringList.toArray(toReturn);
		
		return toReturn;
	}
	
	// Method for removing a file from the list of files.
	private void removeFile(String name)
	{	
		// Using a regular for loop to avoid the 'ConcurrentModificationException' when removing elements from an array list. 
		for (int i = 0; i < javaFileList.size(); i++)
		{
			if (javaFileList.get(i).getName().equals(name))
			{
				System.out.println("File removed: " + javaFileList.get(i).getName());
				javaFileList.remove(javaFileList.get(i));
				break;
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
			if (f.isDirectory() && !checkForExclusion(f))
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
	
	private boolean checkForExclusion(File file)
	{
		System.out.println("Checking for exclusion...");
		if (excludedFolders.contains(file.getName()))
		{
			System.out.println("Directory: " + file.getName() + " is in the exclusion list!");
			return true;
		}
		return false;
	}
	
	private boolean checkForDuplicate(File file)
	{
		for (File f : javaFileList)
		{
			if (file.equals(f))
			{
				System.out.println("File already in list!");
				return true;
			}
		}
		return false;
	}
 }