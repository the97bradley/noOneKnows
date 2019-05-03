package metrixsStructure;

import ast.AST;
import ast.AbstractFieldAccess;
import ast.AbstractMethod;
import ast.AbstractMethodInvocation;
import ast.AbstractStatement;
import ast.AbstractType;
import ast.NodeType;
import projectComponent.ClassContent;
import projectComponent.FileContent;
import projectComponent.MethodContent;
import projectComponent.ProjectContent;

public class LAA extends MetrixsStruct {
	
	public LAA()
	{
		super.id = MetrixsID.LAA;
	}

	@Override
	public void calculate(AST ast, FileContent fileContent, ProjectContent projectContent) {
		// TODO Auto-generated method stub
		for(AbstractType type : ast.getTypes())
		{
			ClassContent cc = fileContent.getClass(type.getName());
			
			for(AbstractMethod am : type.getMethods())
			{
				MethodContent mc = cc.getMethodByName(am.getName());
				int methodLAA = 0;
				for(AbstractStatement as : am.getStatements())
				{
					if(as.getNodeType().equals(NodeType.FIELD_ACCESS))
					{
						AbstractFieldAccess afa = (AbstractFieldAccess)as;
						if(afa.getDeclaringClass().equals(type.getName()))
						{
							
							methodLAA++;
						}
					}
					else if(as.getNodeType().equals(NodeType.METHOD_INVOCATION)) 
					{
						AbstractMethodInvocation ami = (AbstractMethodInvocation)as;
						
						if(ami.getDeclaringClass().equals(type.getName()))
						{
							if(ami.isAccessor())
							{	
								
								methodLAA++;
							}
						}
					}
				}
				double result = (methodLAA>0)?(type.getFields().size()*1.0/methodLAA):0;
				
				
				
				mc.getMetrixsContent().setMetrixStruct(MetrixsID.LAA, result);
			}
			
		}

	}

}
