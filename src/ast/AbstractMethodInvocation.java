package ast;


public class AbstractMethodInvocation extends AbstractStatement{
	
	private String declaringClass;
	private boolean isAccessor;
	private String accessedField;
	
	public AbstractMethodInvocation() {
		super(NodeType.METHOD_INVOCATION);
	}
	
	public AbstractMethodInvocation(String expression, String declaringClass, boolean accessor, String accessedField) {
		this();
		setExpression(expression);
		this.declaringClass = declaringClass;
		this.isAccessor = accessor;
		this.accessedField = accessedField;
	}

	public String getDeclaringClass() {
		return declaringClass;
	}

	public void setDeclaringClass(String declaringClass) {
		this.declaringClass = declaringClass;
	}

	public boolean isAccessor() {
		return isAccessor;
	}

	public void setAccessor(boolean accessor) {
		this.isAccessor = accessor;
	}

	public String getAccessedField() {
		return accessedField;
	}

	public void setAccessedField(String accessedField) {
		this.accessedField = accessedField;
	}

	

}