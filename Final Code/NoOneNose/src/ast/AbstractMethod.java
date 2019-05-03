package ast;

import java.util.List;



public class AbstractMethod {
	
	private String name;
	private int startPosition;
	private int endPosition;
	private String returnType;
	private boolean isConstructor;
	private boolean isVarargs;
	private List<String> modifiers;
	private List<AbstractStatement> statements;
	private List<AbstractParameter> parameters;
	private List<String> thrownsExceptions;
	private int maxDepth;
	private boolean isAccessor;
	private String accessoredField;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}

	public int getEndPosition() {
		return endPosition;
	}

	public void setEndPosition(int endPosition) {
		this.endPosition = endPosition;
	}
	
	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	
	public boolean isConstructor() {
		return isConstructor;
	}

	public void setConstructor(boolean isConstructor) {
		this.isConstructor = isConstructor;
	}
	
	public boolean isVarargs() {
		return isVarargs;
	}

	public void setVarargs(boolean isVarargs) {
		this.isVarargs = isVarargs;
	}
	
	public List<String> getModifiers() {
		return modifiers;
	}

	public void setModifiers(List<String> modifiers) {
		this.modifiers = modifiers;
	}
	
	public List<AbstractStatement> getStatements() {
		return statements;
	}

	public void setStatements(List<AbstractStatement> statements) {
		this.statements = statements;
	}

	public List<AbstractParameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<AbstractParameter> parameters) {
		this.parameters = parameters;
	}

	public List<String> getThrownsExceptions() {
		return thrownsExceptions;
	}

	public void setThrownsExceptions(List<String> thrownsExceptions) {
		this.thrownsExceptions = thrownsExceptions;
	}

	public int getMaxDepth() {
		return maxDepth;
	}

	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}
	
	public boolean isAccessor() {
		return isAccessor;
	}

	public void setAccessor(boolean accessor) {
		this.isAccessor = accessor;
	}

	public String getAccessoredField() {
		return accessoredField;
	}

	public void setAccessoredField(String accessoredField) {
		this.accessoredField = accessoredField;
	}

}
