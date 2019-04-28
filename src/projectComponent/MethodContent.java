package projectComponent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bson.Document;

import metrixsStructure.MetrixsID;

public class MethodContent {
	
	private String name;
	private MetrixsContent metrixsContent = new MetrixsContent();
	
	public MethodContent(String name)
	{
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MetrixsContent getMetrixsContent() {
		return metrixsContent;
	}

	public void setMetrixsContent(MetrixsContent metrixsContent) {
		this.metrixsContent = metrixsContent;
	}
	
	public Document toDocument() {
		Document doc = new Document("name", name).
				append("metrics", metrixsContent.toMetricsDocument()).
				append("codesmells", metrixsContent.getCodeSmellsAsString());
		return doc;
	}
	
	public static Object toDocumentList(Collection<MethodContent> values) {
		List<Document> docs = new ArrayList<>();
		for (MethodContent mc : values) {
			docs.add(mc.toDocument());
		}
		return docs;
	}
	
	public String toString()
	{
		if(metrixsContent.toString()=="")
		{
			return "";
		}
		
		String result = "";
		result = name + " method "+" code smell: "+metrixsContent+"\n";
		if(metrixsContent.hasMetrixStruct(MetrixsID.LOC))
		{
			result+="LOC: "+metrixsContent.getMetrixStruct(MetrixsID.LOC)+"\n";
		}
		
		return result;
	}

}