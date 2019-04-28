package ast;

public class AbstractAnnotationMember {
	
	private String type;
	private String name;
	private String defaultExpression;

	
	
	
	public AbstractAnnotationMember() {
		super();
		// TODO Auto-generated constructor stub
	}



	public AbstractAnnotationMember(String type, String name) {
		this.type = type;
		this.name = name;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDefaultExpression() {
		return defaultExpression;
	}



	public void setDefaultExpression(String defaultExpression) {
		this.defaultExpression = defaultExpression;
	}
	
	

}