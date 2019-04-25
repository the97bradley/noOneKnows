package NoOneNose.GUI;

import java.io.File;
import javax.swing.ImageIcon;
import java.awt.Image;

// Utility class with random useful methods that didn't fit in other classes. 
public class Utils
{
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
}