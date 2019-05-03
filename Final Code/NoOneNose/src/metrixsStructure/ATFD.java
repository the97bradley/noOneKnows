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

public class ATFD extends MetrixsStruct {
	
	public ATFD()
	{
		super.id = MetrixsID.ATFD;
	}
	

	@Override
	public void calculate(AST ast, FileContent fileContent, ProjectContent projectContent) {
		// TODO Auto-generated method stub
		
		
		for(AbstractType type:ast.getTypes())
		{
			ClassContent cc = fileContent.getClass(type.getName());
			int classATFD = 0;
			for(AbstractMethod method:type.getMethods())
			{
				
				MethodContent mc = cc.getMethodByName(method.getName());
				int methodATFD = 0;
				for(AbstractStatement as:method.getStatements())
				{
					
					if(as.getNodeType().equals(NodeType.FIELD_ACCESS))
					{
						AbstractFieldAccess afa = (AbstractFieldAccess)as;
						String dc = afa.getDeclaringClass();
						
						
						if(!(dc.equals(type.getName())))
						{
							classATFD++;
							methodATFD++;
							
						}
						
					}
					else if(as.getNodeType().equals(NodeType.METHOD_INVOCATION))
					{
						AbstractMethodInvocation ami = (AbstractMethodInvocation)as;
						if(ami.isAccessor())
						{
							
							String dc = ami.getDeclaringClass();
							if(!dc.equals(type.getName()))
							{
								classATFD++;
								methodATFD++;
								
							}
						}
						
					}
				}
				
				
				
				
				mc.getMetrixsContent().setMetrixStruct(MetrixsID.ATFD, methodATFD);
			}
			cc.getMetrixsContent().setMetrixStruct(MetrixsID.ATFD, classATFD);
		}

	}
}
