package ast;


public class AbstractClass extends AbstractType{
	
	private boolean isInterface;
	private String superClass;
	
	public AbstractClass()
	{
		setNodeType(NodeType.CLASS_DECLARATION);
	}
	


	public boolean isInterface() {
		return isInterface;
	}



	public void setInterface(boolean isInterface) {
		this.isInterface = isInterface;
	}



	public String getSuperClass() {
		return superClass;
	}

	public void setSuperClass(String superClass) {
		this.superClass = superClass;
	}

	
	


}