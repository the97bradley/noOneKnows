package projectComponent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.bson.Document;

import metrixsStructure.MetrixsID;




public class FileContent {
	
	private String name;
	private Map<String, ClassContent> classContents = new HashMap<>();
	private MetrixsContent metrixsContent = new MetrixsContent();
	

	
	public ClassContent getClass(String name)
	{
		ClassContent cc = classContents.get(name);
		if(cc == null)
		{
			cc = new ClassContent(name);
			classContents.put(name,cc);
		}
		
		return classContents.get(name);
	}
	
	public Collection<ClassContent> getClasses() {
		return classContents.values();
	}
	
	public FileContent(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Map<String, ClassContent> getClassContents() {
		return classContents;
	}
	
	public void setClassContents(Map<String, ClassContent> classContents) {
		this.classContents = classContents;
	}

	public MetrixsContent getMetrixsContent() {
		return metrixsContent;
	}

	public void setMetrixsContent(MetrixsContent metrixsContent) {
		this.metrixsContent = metrixsContent;
	}

	public Document toDocument() {
		// TODO Auto-generated method stub
		Document doc = new Document();
		doc.append("filename", name).
			append("filehash", StringUtils.encodeToCRC32(name)).
			append("metrics", metrixsContent.toMetricsDocument()).
			append("classes", ClassContent.toDocumentList(classContents.values()));
		return doc;
	}
	
	public String toString()
	{
		String result="\nfile: "+name+" code smell: "+metrixsContent+"\n\n";
		
		for(ClassContent cc:classContents.values())
		{
			result+=cc;
		}
		
		return result;
	}
	

	

}