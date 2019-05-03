package ast;

import java.util.List;




public class AbstractEnum extends AbstractType{
	
	private List<AbstractEnumConstant> constants;
	public AbstractEnum() {
		setNodeType(NodeType.ENUM_DECLARATION);
	}
	
	public List<AbstractEnumConstant> getConstants() {
		return constants;
	}

	public void setConstants(List<AbstractEnumConstant> constants) {
		this.constants = constants;
	}

}
