package projectComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.bson.Document;

import codesmell.CodeSmellId;
import metrixsStructure.MetrixsID;

public class MetrixsContent {
	
	private Map<MetrixsID,Number> metrixStruct = new HashMap<>();
	private Set<CodeSmellId> codeSmells= new HashSet<CodeSmellId>();
	
	public List<String> getCodeSmellsAsString()
	{
		List<String> list = new ArrayList<String>();
		
		for(CodeSmellId cid: codeSmells)
		{
			list.add(cid.name());
		}
		
		return list;
	}
	
	public boolean hasCodeSmell(CodeSmellId id) {
		return codeSmells.contains(id);
	}
	
	public boolean hasMetrixStruct(MetrixsID smell)
	{
		return metrixStruct.containsKey(smell);
	}
	
	public Number getMetrixStruct(MetrixsID smell)
	{
		return metrixStruct.get(smell);
	}
	
	public <T extends Number> T getMetrixStruct(MetrixsID smell,Class<T> type)
	{
		return type.cast(metrixStruct.get(smell));
	}
	
	public void setMetrixStruct(MetrixsID smell,Number N)
	{
		metrixStruct.put(smell, N);
	}
	
	public void setCodeSmell(CodeSmellId id)
	{
		codeSmells.add(id);
	}
	
	public void updateStruct(MetrixsID id,int value)
	{
		int newvalue = value;
		if(metrixStruct.containsKey(id))
		{
			newvalue +=metrixStruct.get(id).intValue();
		}
		metrixStruct.put(id, newvalue);
	}
	
	public void updateStruct(MetrixsID id,long value)
	{
		long newvalue = value;
		if(metrixStruct.containsKey(id))
		{
			newvalue +=metrixStruct.get(id).longValue();
		}
		metrixStruct.put(id, newvalue);
	}
	
	public void updateStruct(MetrixsID id,float value)
	{
		float newvalue = value;
		if(metrixStruct.containsKey(id))
		{
			newvalue +=metrixStruct.get(id).floatValue();
		}
		metrixStruct.put(id, newvalue);
	}
	
	public void updateStruct(MetrixsID id,double value)
	{
		double newvalue = value;
		if(metrixStruct.containsKey(id))
		{
			newvalue +=metrixStruct.get(id).doubleValue();
		}
		metrixStruct.put(id, newvalue);
	}
	
	public void addOneToSmellStruct(MetrixsID sid)
	{
		updateStruct(sid,1);
	}


	public Map<MetrixsID, Number> getMetrixStruct() {
		return metrixStruct;
	}

	public void setMetrixStruct(Map<MetrixsID, Number> metrixStruct) {
		this.metrixStruct = metrixStruct;
	}

	public Set<CodeSmellId> getCodeSmells()
	{
		return codeSmells;
	}
	
	public void setCodeSmells(Set<CodeSmellId> newCodeSmells)
	{
		this.codeSmells = newCodeSmells;
	}

	public List<Document> toMetricsDocument() {
		// TODO Auto-generated method stub
		List<Document> documents = new ArrayList<Document>();
		for (Entry<MetrixsID, Number> entry : metrixStruct.entrySet()) {
			documents.add(new Document("name", entry.getKey().name()).append("value", entry.getValue()));
		}
		return documents;
	}
	
	public String toString()
	{
		if(codeSmells.isEmpty())
		{
			return "";
		}
		String result = "";
		for(CodeSmellId id:codeSmells)
		{
			result+=id.name()+" ";
		}
		return result;
	}
}