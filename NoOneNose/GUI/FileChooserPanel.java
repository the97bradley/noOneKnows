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
	private ArrayList<File> tempFileList = new ArrayList<File>();
	private ArrayList<String> excludedFolders = new ArrayList<String>();
	
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
		mainTitleText.setFont(new Font("Brandon Text Light", Font.PLAIN, 32));
		mainTitleText.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 0));
		mainTitle.add(mainTitleText, BorderLayout.NORTH);
		
		JTextArea titleSubText = new JTextArea();
		titleSubText.setOpaque(false);
		titleSubText.setForeground(Color.GRAY);
		titleSubText.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
		titleSubText.setFont(new Font("Brandon Text Light", Font.PLAIN, 16));
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
		
		JButton excludeButton = new JButton("Exclude a Directory");
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
		
		JPanel buttonPanel = new JPanel();
		
		
		// Setup for the layout.
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(20, 20, 20, 20);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.ipadx = 600;
		gbc.ipady = 300;
		mainPanel.add(filePanel, gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.ipadx = 50;
		gbc.ipady = 30;
		mainPanel.add(fileButton, gbc);
		
		gbc.gridx = 1;
		mainPanel.add(folderButton, gbc);		
		
		gbc.gridx = 2;
		mainPanel.add(removeFileButton, gbc);
		
		gbc.gridx = 3;
		mainPanel.add(excludeButton, gbc);
		
		gbc.gridy = 2;
		gbc.weighty = 2;
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