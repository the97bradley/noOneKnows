package metrixsStructure;

import ast.AST;
import ast.AbstractMethod;
import ast.AbstractType;
import projectComponent.ClassContent;
import projectComponent.FileContent;
import projectComponent.ProjectContent;

public class NOAM extends MetrixsStruct {
	
	public NOAM()
	{
		super.id = MetrixsID.NOAM;
	}

	@Override
	public void calculate(AST ast, FileContent fileContent, ProjectContent projectContent) {
		// TODO Auto-generated method stub
		for(AbstractType type : ast.getTypes())
		{
			ClassContent cc = fileContent.getClass(type.getName());
			int noam = 0;
			
			for(AbstractMethod am:type.getMethods())
			{
				if(am.getModifiers().contains("public")&&am.isAccessor())
				{
					noam++;
				}
			}
			cc.getMetrixsContent().setMetrixStruct(MetrixsID.NOAM, noam);
		}

	}

}
