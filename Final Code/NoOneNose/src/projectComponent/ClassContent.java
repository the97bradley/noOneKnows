package projectComponent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import metrixsStructure.MetrixsID;

public class ClassContent {

	
	private String name;
	private String type;
	private Map<String, MethodContent> methodsContent = new HashMap<>();
	private MetrixsContent metrixsContent = new MetrixsContent();
	
	public ClassContent(String name) {
		this.name = name;
	}
	
	public ClassContent(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	public Collection<MethodContent> getMethods() {
		return methodsContent.values();
	}
	
	public MethodContent getMethodByName(String name)
	{
		MethodContent mc = methodsContent.get(name);
		if(mc == null)
		{
			mc = new MethodContent(name);
			methodsContent.put(name, mc);
		}
		return mc;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String, MethodContent> getMethodsContent() {
		return methodsContent;
	}

	public void setMethodsContent(Map<String, MethodContent> methods) {
		this.methodsContent = methods;
	}

	public MetrixsContent getMetrixsContent() {
		return metrixsContent;
	}

	public void setMetrixsContent(MetrixsContent metrixsContent) {
		this.metrixsContent = metrixsContent;
	}
	
	public String toString()
	{
		String result = "Class: " + name+ " has code smell : "+metrixsContent+"\n";
		String methodResult ="";
		
		

		for(MethodContent mc:methodsContent.values())
		{
			result += mc;
			methodResult+=mc;
		}
		if(methodResult=="")
		{
			result+="methods are all clear.\n";
		}

		
		return result;
	}
	
	
	
	
	
	
}