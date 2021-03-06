package ast;

import java.util.List;
/*
 * 
 * 
 */
/**
 * 
 * @author zhang
 *
 */


public class AbstractAnnotation extends AbstractType{
	
	private List<AbstractAnnotationMember> members;
	
	public AbstractAnnotation() {
		setNodeType(NodeType.ANNOTATION_DECLARATION);
	}

	public List<AbstractAnnotationMember> getMembers() {
		return members;
	}

	public void setMembers(List<AbstractAnnotationMember> members) {
		this.members = members;
	}


}
