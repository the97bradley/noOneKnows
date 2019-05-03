package ast;



public class AbstractFieldAccess extends AbstractStatement{

	public AbstractFieldAccess() {
		super(NodeType.FIELD_ACCESS);
		
	}
	private String type;
	private String declaringClass;
	private boolean primitive;
	
	public AbstractFieldAccess(String expression, String type, String declaringClass, boolean primitive) {
		this();
		setExpression(expression);
		this.type = type;
		this.declaringClass = declaringClass;
		this.primitive = primitive;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDeclaringClass() {
		return declaringClass;
	}

	public void setDeclaringClass(String declaringClass) {
		this.declaringClass = declaringClass;
	}

	public boolean isPrimitive() {
		return primitive;
	}

	public void setPrimitive(boolean primitive) {
		this.primitive = primitive;
	}


	
}
