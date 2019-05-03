package metrixsStructure;

import ast.AST;
import ast.AbstractMethod;
import ast.AbstractStatement;
import ast.AbstractType;
import ast.NodeType;
import projectComponent.ClassContent;
import projectComponent.FileContent;
import projectComponent.MethodContent;
import projectComponent.ProjectContent;

public class SW extends MetrixsStruct{
	
	public SW()
	{
		super.id = MetrixsID.SW;
	}

	@Override
	public void calculate(AST ast, FileContent fileContent, ProjectContent projectContent) {
		// TODO Auto-generated method stub
		for(AbstractType type : ast.getTypes())
		{
			ClassContent cc = fileContent.getClass(type.getName());
			for(AbstractMethod am:type.getMethods())
			{
				boolean sw = false;
				MethodContent mc = cc.getMethodByName(am.getName());
				for(AbstractStatement as:am.getStatements())
				{
					if(as.getNodeType().equals(NodeType.SWITCH))
					{
						sw = true;
					}
				}
				if(sw)
				{
					mc.getMetrixsContent().setMetrixStruct(MetrixsID.SW, 1);
				}
			}
		}
		
	}
	

}
