package ast;

import java.util.List;

public class AbstractEnumConstant {
	
	private String name;
	private List<String> arguments;
	
	public AbstractEnumConstant() {
	}
	
	public AbstractEnumConstant(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getArguments() {
		return arguments;
	}

	public void setArguments(List<String> arguments) {
		this.arguments = arguments;
	}

}