package metrixsStructure;

import ast.AST;
import ast.AbstractField;
import ast.AbstractType;
import projectComponent.ClassContent;
import projectComponent.FileContent;
import projectComponent.ProjectContent;

public class NOF extends MetrixsStruct{

	public NOF()
	{
		super.id = MetrixsID.NOF;
		
	}
	
	@Override
	public void calculate(AST ast, FileContent fileContent, ProjectContent projectContent) {
		// TODO Auto-generated method stub
		
		for(AbstractType at:ast.getTypes())
		{

			ClassContent cc = fileContent.getClass(at.getName());
			int PriField=0;
			for(AbstractField field:at.getFields())
			{
				if(field.isPrimitive())
				{
					PriField++;
				}
			}
			cc.getMetrixsContent().setMetrixStruct(MetrixsID.NOF, PriField);

			
		}
		
	}

	@Override
	public void clean(ProjectContent projectcontent) {
		// TODO Auto-generated method stub
		
	}

}
