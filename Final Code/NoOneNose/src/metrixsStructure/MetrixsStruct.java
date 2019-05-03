package metrixsStructure;

import ast.AST;
import projectComponent.FileContent;
import projectComponent.ProjectContent;

public abstract class MetrixsStruct {
	protected MetrixsID id;
	protected MetrixsID[] requiredID;
	
	public abstract void calculate(AST ast, FileContent fileContent, ProjectContent projectContent);
	
	
	public MetrixsID getID()
	{
		return id;
	}
	
	public MetrixsID[] getRequiredID()
	{
		return requiredID;
	}
}
