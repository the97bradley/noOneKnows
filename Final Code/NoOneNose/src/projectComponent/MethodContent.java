package projectComponent;

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
	
	
	public String toString()
	{
		if(metrixsContent.toString()=="")
		{
			return "";
		}
		
		String result = "";
		result =  "Method: "+name +" has code smell: "+metrixsContent+"\n";


		
		return result;
	}

}