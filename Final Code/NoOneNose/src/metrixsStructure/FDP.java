package metrixsStructure;

import java.util.ArrayList;
import java.util.List;

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

public class FDP extends MetrixsStruct {
	
	public FDP()
	{
		super.id = MetrixsID.FDP;
		
	}

	@Override
	public void calculate(AST ast, FileContent fileContent, ProjectContent projectContent) {
		// TODO Auto-generated method stub
		
		for(AbstractType type : ast.getTypes())
		{
			ClassContent cc = fileContent.getClass(type.getName());
			
			List<String> numberClassClass = new ArrayList<String>();
			for(AbstractMethod am : type.getMethods())
			{
				List<String> numberClassMethod = new ArrayList<String>();
				MethodContent mc = cc.getMethodByName(am.getName());
				
				for(AbstractStatement as : am.getStatements())
				{
					if(as.getNodeType().equals(NodeType.FIELD_ACCESS))
					{
						AbstractFieldAccess afa = (AbstractFieldAccess)as;
						if(!numberClassClass.contains(afa.getDeclaringClass()))
						{
							numberClassClass.add(afa.getDeclaringClass());
						}
						if(!numberClassMethod.contains(afa.getDeclaringClass()))
						{
							numberClassMethod.add(afa.getDeclaringClass());
						}
					}
					else if(as.getNodeType().equals(NodeType.METHOD_INVOCATION))
					{
						AbstractMethodInvocation ami = (AbstractMethodInvocation)as;
						if(ami.isAccessor())
						{
							if(!numberClassClass.contains(ami.getDeclaringClass()))
							{
								numberClassClass.add(ami.getDeclaringClass());
							}
							if(!numberClassMethod.contains(ami.getDeclaringClass()))
							{
								numberClassMethod.add(ami.getDeclaringClass());
							}
						}
							
					}
				}
				
				mc.getMetrixsContent().setMetrixStruct(MetrixsID.FDP, numberClassMethod.size());
				
			}
			
			cc.getMetrixsContent().setMetrixStruct(MetrixsID.FDP, numberClassClass.size());
		}

	}
}
