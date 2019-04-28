package projectComponent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;



public class ProjectContent {

	private Map<String, FileContent> files = new HashMap<>();
	private Map<String, ClassContent> orphanClasses = new HashMap<>();
	
	public Collection<FileContent> getAllFiles()
	{
		return files.values();
		
	}
	
	public FileContent getFileByName(String name)
	{
		return files.get(name);
	}
	
	public ClassContent getClassByName(String name)
	{
		for(FileContent fc:files.values())
		{
			
			ClassContent cc = fc.getClassContents().get(name);
			if(cc!=null)
			{
				return cc;
			}
			
		}
		
		ClassContent cc = orphanClasses.get(name);
		if(cc!=null)
		{
			cc = new ClassContent(name);
			orphanClasses.put(name, cc);
		}
		return null;
		
	}
	
	public void addFileContent(FileContent fileContent)
	{
		files.put(fileContent.getName(), fileContent);
		
		if (orphanClasses.isEmpty()) {
			return;
		}
		
		for(ClassContent cc :fileContent.getClassContents().values())
		{
			ClassContent orphanClass = orphanClasses.get(cc.getName());
			if(orphanClass!=null)
			{
				orphanClasses.remove(cc.getName());
				cc.setMetrixsContent(orphanClass.getMetrixsContent());
				for(MethodContent mc : orphanClass.getMethods())
				{
					MethodContent orphanMethod = orphanClass.getMethodsContent().get(mc.getName());
					if(orphanMethod!=null)
					{
						orphanClass.getMethodsContent().remove(mc.getName());
						mc.setMetrixsContent(orphanMethod.getMetrixsContent());
					}
				}
			}
		}
	}
	
	public String toString()
	{
		String result =  "Project: \n\n\n" ;
		for(FileContent fc:files.values())
		{
			result+=fc;
		}
		
		return result;
	}
	
}
