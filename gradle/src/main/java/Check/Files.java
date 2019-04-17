package Check;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Files {
	private List<FileComponent> fileComponents = new ArrayList<FileComponent>();
	private FileComponent fileComponent;
	
	public Files(ArrayList<File> files) throws IOException
	{
		fillList(files);
	}

	private void fillList(ArrayList<File> files) throws IOException {
		// TODO Auto-generated method stub
		for(File f:files)
		{
			fileComponents.add(new FileComponent(f));
		}
	}

}
