package GUI;

import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.FontFormatException;
import javax.swing.ImageIcon;

import codesmell.CodeSmellId;

import java.awt.Image;
import java.awt.Color;

// Utility class with random useful methods that didn't fit in other classes. 
public class Utils
{
	// Global font to used throughout the GUI. Setting a default font.
	public static Font FONT = new Font("Segoe UI Light", Font.PLAIN, 16);
	
	// Some colours that were frequently used were put here instead.
	public static Color subComponentColour = new Color(40, 45, 60);
	public static Color subComponentBorderColour = new Color(80, 90, 100);
	public static Color subComponentTitleColour = new Color(200, 210, 225);
	public static Color subComponentTextColour = new Color(180, 185, 190);
	
	// List of smells for use in the setting panel. 
	public static String[] SMELL_LIST = 
	{
		CodeSmellId.COMPLEX_METHOD.toString(), CodeSmellId.GOD_CLASS.toString(), 
		CodeSmellId.LONG_METHOD.toString(), CodeSmellId.DATA_CLASS.toString(), 
		CodeSmellId.FEATURE_ENVY.toString(), CodeSmellId.LARGE_CLASS.toString(), CodeSmellId.PRIMITIVE_OBSESSION.toString(),
		CodeSmellId.LONG_PARAMETER_LIST.toString(), CodeSmellId.LAZY_CLASS.toString(),CodeSmellId.SWITCH_STATEMENT.toString()
	};
	
	// Method for resizing icons. Moved this method here since the line required to do it was quite long.
	public static ImageIcon resizeIcon(ImageIcon image, int width, int height)
	{
		ImageIcon newImage = new ImageIcon(image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
		return newImage;
	}
	
	// Method for finding the file type (file extension) of a given file.
	public static String getFileExtension(File file)
	{
		String fileName = file.getName();
		int dotIndex = fileName.lastIndexOf('.');
		return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
	}
	
	// Method for specifying a font using a font file, if needed. 
	public static void getFont()
	{
		try
		{
			FONT = Font.createFont(Font.TRUETYPE_FONT, new File("GUI\\Fonts\\Raleway-Regular.ttf"));
			System.out.println("Font read successfully.");
		}
		catch (IOException | FontFormatException e)
		{
			//e.printStackTrace();
			System.out.println("Failed to load font file.");
		}
	}
	
}