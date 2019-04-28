package NoOneNose.GUI;

import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.FontFormatException;
import javax.swing.ImageIcon;
import java.awt.Image;

// Utility class with random useful methods that didn't fit in other classes. 
public class Utils
{
	public static Font FONT = null;
	public static String[] SMELL_LIST = 
	{
		"Brain Class", "Brain Method", "Complex Method", "God Class", 
		"Long Method", "Refused Parent Bequest", "Data Class", 
		"Depth of Inheritance Tree", "Feature Envy", "Large Class", 
		"Primitive Obsession", "Long Parameter List" 
	};
	
	public static ImageIcon resizeIcon(ImageIcon image, int width, int height)
	{
		ImageIcon newImage = new ImageIcon(image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
		return newImage;
	}
	
	public static String getFileExtension(File file)
	{
		String fileName = file.getName();
		int dotIndex = fileName.lastIndexOf('.');
		return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
	}
	
	public static void getFont()
	{
		try
		{
			FONT = Font.createFont(Font.TRUETYPE_FONT, new File("NoOneNose\\GUI\\Fonts\\Brandon-Text-Light.otf"));
		}
		catch (IOException | FontFormatException e)
		{
			e.printStackTrace();
		}
	}
	
}